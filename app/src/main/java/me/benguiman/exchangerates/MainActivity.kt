package me.benguiman.exchangerates

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import me.benguiman.exchangerates.data.ExchangeRate
import me.benguiman.exchangerates.ui.ExchangeRateViewModel
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
    private fun ExchangeRatesApp(exchangeRateViewModel: ExchangeRateViewModel = viewModel()) {
        val allExchangeRatesUiState by exchangeRateViewModel.allExchangeRatesUiState.collectAsState()
        ExchangeRatesTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                if (allExchangeRatesUiState.errorMessage.isEmpty()) {
                    ExchangeRateList(allExchangeRatesUiState.exchangeRates)
                } else {
                    Text(text = allExchangeRatesUiState.errorMessage)
                }
            }
        }
    }
}

@Composable
fun ExchangeRateList(exchangeRateList: List<ExchangeRate> = emptyList()) {
    LazyColumn {
        items(items = exchangeRateList) {
            ExchangeRateItem(exchangeRate = it)
        }
    }
}

@Composable
fun ExchangeRateItem(exchangeRate: ExchangeRate) {
    Row {
        Image(imageVector = Icons.Filled.Star, contentDescription = "")
        Column {
            Text(exchangeRate.currency)
            Text("$${exchangeRate.exchangeRate}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExchangeRateItem(exchangeRate = ExchangeRate("USD", 1.35))
}