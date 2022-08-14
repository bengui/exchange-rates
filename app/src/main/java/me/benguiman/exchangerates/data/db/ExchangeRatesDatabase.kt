package me.benguiman.exchangerates.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.benguiman.exchangerates.data.db.SeedDatabaseWorker.Companion.KEY_FILENAME
import java.io.InputStreamReader

const val DATABASE_NAME = "exchange_rates_database"
const val CURRENCY_CSV_FILENAME = "currency_data_20220814.csv"

@Database(entities = [CurrencyEntity::class], version = 1, exportSchema = false)
abstract class ExchangeRatesDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao


    companion object {

        @Volatile
        private var instance: ExchangeRatesDatabase? = null

        fun getInstance(context: Context): ExchangeRatesDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(
            context: Context
        ): ExchangeRatesDatabase {
            return Room.databaseBuilder(context, ExchangeRatesDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                            .setInputData(workDataOf(KEY_FILENAME to CURRENCY_CSV_FILENAME))
                            .build()

                        WorkManager.getInstance(context).enqueue(request)
                    }
                }).build()
        }
    }
}

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME)
                ?: throw IllegalArgumentException("Filename missing")
            val reader = applicationContext.assets.open(filename).reader()
            val currencyEntityList = parseCurrencyEntityListFromCsv(reader)
            val database = ExchangeRatesDatabase.getInstance(applicationContext)
            database.currencyDao().insertAll(currencyEntityList)

            Result.success()
        } catch (e: Throwable) {
            Log.e(TAG, "Failure loading values into the database")
            Result.failure()
        }
    }

    companion object {
        const val TAG = "SeedDatabaseWorker"
        const val KEY_FILENAME = "SEED_DATABASE_FILENAME"
    }
}

/**
 *
 * Parse CSV with the following structure:
 *
 * [0] region
 * [1] currency_name
 * [2] currency_code_iso_4217
 * [3] numeric_code_iso_4217
 * [4] alpha_2_code_iso_3166
 * [5] alpha_3_code_iso_3166
 *
 * where the first line are the headers
 */
fun parseCurrencyEntityListFromCsv(reader: InputStreamReader): List<CurrencyEntity> {
    val currencyList = mutableListOf<CurrencyEntity>()
    val lines = reader.readLines()
    for (i in 1..lines.lastIndex) {
        val fields = lines[i].split(",")
        currencyList.add(
            CurrencyEntity(
                currencyCode = fields[2],
                currencyName = fields[1],
                region = fields[0],
                twoLetterRegionCode = fields[4]
            )
        )
    }

    return currencyList
}
