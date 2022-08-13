package me.benguiman.exchangerates.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val currencyName: String,
    @ColumnInfo(name = "code") val currencyCode: String,
    @ColumnInfo(name = "region_name") val region: String,
    @ColumnInfo(name = "region_code") val twoLetterRegionCode: String
)