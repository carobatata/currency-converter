package com.github.carobatata.currencyconverter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.carobatata.currencyconverter.data.model.Rates
import com.github.carobatata.currencyconverter.domain.CurrencyConverterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.round
import kotlin.reflect.full.memberProperties

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val currencyConverterRepository: CurrencyConverterRepository
) : ViewModel() {

    private val _conversionUiState =
        MutableStateFlow<CurrencyConverterUiState>(CurrencyConverterUiState.Empty)
    val conversionUiState: StateFlow<CurrencyConverterUiState> = _conversionUiState

    fun convert(
        amountString: String,
        fromCurrency: String,
        toCurrency: String,
    ) {
        val fromAmount = amountString.toFloatOrNull()
        if (fromAmount == null) {
            _conversionUiState.value = CurrencyConverterUiState.Error
            return
        }

        viewModelScope.launch {
            _conversionUiState.value = CurrencyConverterUiState.Loading

            currencyConverterRepository.getRates(fromCurrency).fold(
                onSuccess = { ratesResponse ->
                    val rate = getRateForCurrency(toCurrency, ratesResponse.rates)
                    if (rate == null) {
                        _conversionUiState.value = CurrencyConverterUiState.Error
                    } else {
                        val convertedCurrency = round(fromAmount * rate * 100) / 100
                        _conversionUiState.value =
                            CurrencyConverterUiState.Success("$fromAmount $fromCurrency = $convertedCurrency $toCurrency")
                    }
                },
                onFailure = {
                    _conversionUiState.value = CurrencyConverterUiState.Error
                }
            )
        }
    }

    fun getRateForCurrency(currency: String, rates: Rates): Double? {
        val ratesMap = Rates::class.memberProperties
            .associateBy({ it.name.uppercase() }, { it.get(rates) as Double })

        return ratesMap[currency.uppercase()]
    }
}