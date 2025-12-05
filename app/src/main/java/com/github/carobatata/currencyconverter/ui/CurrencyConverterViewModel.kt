package com.github.carobatata.currencyconverter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.carobatata.currencyconverter.domain.CurrencyConverterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.round

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val currencyConverterRepository: CurrencyConverterRepository
) : ViewModel() {

    private val _fromCurrency = MutableStateFlow("USD")
    val fromCurrency: StateFlow<String> = _fromCurrency

    private val _toCurrency = MutableStateFlow("EUR")
    val toCurrency: StateFlow<String> = _toCurrency

    private val _amount = MutableStateFlow(0.0)
    val amount: StateFlow<Double> = _amount

    private val _convertedAmount = MutableStateFlow<Double?>(null)
    val convertedAmount: StateFlow<Double?> = _convertedAmount

//    private val _conversionUiState =
//        MutableStateFlow<CurrencyConverterUiState>(CurrencyConverterUiState.Empty)
//    val conversionUiState: StateFlow<CurrencyConverterUiState> = _conversionUiState

    fun setFromCurrency(currency: String) {
        _fromCurrency.value = currency
        convertAmount()
    }

    fun setToCurrency(currency: String) {
        _toCurrency.value = currency
        convertAmount()
    }

    fun setAmount(value: Double) {
        _amount.value = value
        convertAmount()
    }

    private fun convertAmount() {
        viewModelScope.launch {
            currencyConverterRepository.getRates(_fromCurrency.value).fold(
                onSuccess = { ratesResponse ->
                    val rate = getRateForCurrency(_toCurrency.value, ratesResponse.rates)
                    _convertedAmount.value = if (rate != null) {
                        round(_amount.value * rate * 100) / 100
                    } else {
                        0.0
                    }
                },
                onFailure = {
                    _convertedAmount.value = 0.0
                }
            )
        }
    }

//    fun convert(
//        amountString: String,
//        fromCurrency: String,
//        toCurrency: String,
//    ) {
//        val fromAmount = amountString.toFloatOrNull()
//        if (fromAmount == null) {
//            _conversionUiState.value = CurrencyConverterUiState.Error
//            return
//        }
//
//        viewModelScope.launch {
//            _conversionUiState.value = CurrencyConverterUiState.Loading
//
//            currencyConverterRepository.getRates(fromCurrency).fold(
//                onSuccess = { ratesResponse ->
//                    val rate = getRateForCurrency(toCurrency, ratesResponse.rates)
//                    if (rate == null) {
//                        _conversionUiState.value = CurrencyConverterUiState.Error
//                    } else {
//                        val convertedCurrency = round(fromAmount * rate * 100) / 100
//                        _conversionUiState.value =
//                            CurrencyConverterUiState.Success("$fromAmount $fromCurrency = $convertedCurrency $toCurrency")
//                    }
//                },
//                onFailure = {
//                    _conversionUiState.value = CurrencyConverterUiState.Error
//                }
//            )
//        }
//    }

    fun getRateForCurrency(currency: String, rates: Map<String, Double?>): Double? {
        return rates[currency.uppercase()]
    }
}