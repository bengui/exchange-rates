package me.benguiman.exchangerates.ui.all

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import me.benguiman.exchangerates.data.ExchangeRate
import me.benguiman.exchangerates.ui.AllExchangeRatesViewModel

@Composable
fun AllExchangeRatesScreen(
    allExchangeRatesViewModel: AllExchangeRatesViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val allExchangeRatesUiState by allExchangeRatesViewModel.allExchangeRatesUiState.collectAsState()

    if (allExchangeRatesUiState.errorMessage.isEmpty()) {
        ExchangeRateList(
            exchangeRateList = allExchangeRatesUiState.exchangeRates,
            modifier = modifier
        )
    } else {
        Text(text = allExchangeRatesUiState.errorMessage)
    }
}

@Composable
fun ExchangeRateList(
    exchangeRateList: List<ExchangeRate> = emptyList(),
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = exchangeRateList) {
            ExchangeRateItem(exchangeRate = it)
        }
    }
}

@Composable
fun ExchangeRateItem(
    exchangeRate: ExchangeRate,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val flagResourceId = remember {
        context.resources.getIdentifier(
            exchangeRate.twoLetterCountryCode,
            "drawable",
            context.packageName
        )
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = flagResourceId),
            contentDescription = exchangeRate.currencyName,
            modifier = Modifier
                .size(48.dp)
        )
        Text(
            exchangeRate.currencyName,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
        )
        Text(
            "${exchangeRate.currency} ${exchangeRate.exchangeRate}",
            style = MaterialTheme.typography.h6
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExchangeRateItem(
        exchangeRate = ExchangeRate(
            "USD",
            1.35,
            "us",
            "US Dollar"
        )
    )
}