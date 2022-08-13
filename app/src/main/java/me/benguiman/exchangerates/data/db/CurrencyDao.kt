package me.benguiman.exchangerates.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency")
    fun getAll(): List<CurrencyEntity>

    @Query("SELECT * FROM currency WHERE currency.code = :currencyCode LIMIT 1")
    fun findByCurrencyCode(currencyCode: String): CurrencyEntity

    @Insert
    fun insertAll(vararg currencies: List<CurrencyEntity>)

    @Delete
    fun delete(currencyEntity: CurrencyEntity)
}