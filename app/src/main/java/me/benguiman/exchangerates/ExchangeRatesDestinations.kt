package me.benguiman.exchangerates

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector


interface ExchangeRatesDestination {
    val route: String
    val icon: ImageVector

    @get:StringRes
    val title: Int
}

object All : ExchangeRatesDestination {
    override val route = "all"
    override val icon: ImageVector = Icons.Filled.List
    override val title: Int = R.string.all_currency_list_title
}

object Favorites : ExchangeRatesDestination {
    override val route = "favorites"
    override val icon: ImageVector = Icons.Filled.Home
    override val title: Int = R.string.home_screen_title
}

val exchangeRatesScreens = listOf(All, Favorites)