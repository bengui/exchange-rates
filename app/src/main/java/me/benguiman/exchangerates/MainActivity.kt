package me.benguiman.exchangerates

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.benguiman.exchangerates.ui.AllExchangeRatesViewModel
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
}

@Composable
private fun ExchangeRatesApp(
    navController: NavHostController = rememberNavController()
) {
    val currentScreen =
        exchangeRatesScreens.find { navController.currentDestination?.route == it.route } ?: All
    ExchangeRatesTheme {
        Scaffold(
            topBar = { ExchangeTopAppBar(currentScreen) },
            bottomBar = { ExchangeBottomAppBar() }
        ) {
            ExchangeRatesNavHost(navController)
        }
    }
}

@Composable
private fun ExchangeTopAppBar(currentScreen: ExchangeRatesDestination) {
    TopAppBar {
        Text(
            stringResource(id = currentScreen.title),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ExchangeBottomAppBar() {
    BottomAppBar {
        Icon(
            imageVector = Favorites.icon,
            contentDescription = stringResource(id = Favorites.title),
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = All.icon,
            contentDescription = stringResource(id = All.title),
            modifier = Modifier.weight(1f)
        )
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
            val viewModel = hiltViewModel<AllExchangeRatesViewModel>()
            AllExchangeRatesScreen(viewModel)
        }
    }
}

@Preview
@Composable
fun ExchangeRatesAppPreview() {
    ExchangeRatesApp()
}