package me.benguiman.exchangerates.ui

import android.util.Log
import java.io.IOException

fun getMessageFromThrowable(e: Throwable): String {
    Log.e("Exchange Rates Error logger", e.message ?: e.toString())
    return when (e) {
        is IOException -> "Error retrieving the exchange rates"
        else -> "An unexpected error happened"
    }
}