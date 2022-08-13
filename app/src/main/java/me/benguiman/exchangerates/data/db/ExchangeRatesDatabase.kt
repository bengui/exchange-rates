package me.benguiman.exchangerates.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val DATABASE_NAME = "exchange_rates_database"

@Database(entities = [CurrencyEntity::class], version = 1)
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
            val currencyEntityList = parseCurrencyEntityListFromCsv(filename)
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

fun parseCurrencyEntityListFromCsv(filename: String): List<CurrencyEntity> {
    TODO()
}
