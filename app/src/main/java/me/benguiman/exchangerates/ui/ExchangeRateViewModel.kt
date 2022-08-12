package me.benguiman.exchangerates.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.benguiman.exchangerates.domain.GetAllExchangeRatesUseCase
import javax.inject.Inject

@HiltViewModel
class ExchangeRateViewModel @Inject constructor(
    private val getAllExchangeRatesUseCase: GetAllExchangeRatesUseCase
) : ViewModel() {

    private val _allExchangeUiState: MutableStateFlow<AllExchangeRatesUiState> =
        MutableStateFlow(AllExchangeRatesUiState())

    val allExchangeRatesUiState: StateFlow<AllExchangeRatesUiState>
        get() = _allExchangeUiState.asStateFlow()

    var getAllExchangeRatesJob: Job? = null

    init {
        getAllExchangeRates()
    }

    fun getAllExchangeRates() {
        getAllExchangeRatesJob?.cancel()
        viewModelScope.launch {
            try {
                val exchangeRates = getAllExchangeRatesUseCase()
                _allExchangeUiState.update {
                    it.copy(exchangeRates = exchangeRates)
                }
            } catch (e: Exception) {
                _allExchangeUiState.update {
                    it.copy(errorMessage = getMessageFromThrowable(e))
                }
            }
        }
    }

    fun dismissErrorMessage() {
        _allExchangeUiState.update {
            it.copy(errorMessage = "")
        }
    }
}