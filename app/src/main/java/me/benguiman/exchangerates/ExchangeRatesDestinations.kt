package me.benguiman.exchangerates

interface ExchangeRatesDestination {
    val route: String
}

object All : ExchangeRatesDestination {
    override val route = "all"
}

object Favorites : ExchangeRatesDestination {
    override val route = "favorites"
}