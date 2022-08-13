package me.benguiman.exchangerates

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.benguiman.exchangerates.ui.all.AllExchangeRatesScreen
import me.benguiman.exchangerates.ui.theme.ExchangeRatesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExchangeRatesApp()
        }
    }

    @Composable
    private fun ExchangeRatesApp(
        navController: NavHostController = rememberNavController()
    ) {
        ExchangeRatesTheme {
            Scaffold(
                bottomBar = {
                    BottomAppBar {

                    }
                }
            ) {
                ExchangeRatesNavHost(navController)
            }
        }
    }

}

@Composable
private fun ExchangeRatesNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = All.route,
        modifier = modifier
    ) {
        composable(route = All.route) {
            AllExchangeRatesScreen()
        }
    }
}