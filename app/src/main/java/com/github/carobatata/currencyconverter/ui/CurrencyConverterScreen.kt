package com.github.carobatata.currencyconverter.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun CurrencyConverterScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        CurrencyCard()
    }
}