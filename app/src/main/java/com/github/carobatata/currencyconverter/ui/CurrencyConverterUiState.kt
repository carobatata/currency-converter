package com.github.carobatata.currencyconverter.ui

sealed class CurrencyConverterUiState {
    data object Loading : CurrencyConverterUiState()
    data object Error : CurrencyConverterUiState()
    data object Empty : CurrencyConverterUiState()
    data class Success(val resultText: String) : CurrencyConverterUiState()
}