package com.github.carobatata.currencyconverter.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.github.carobatata.currencyconverter.ui.theme.regular_padding

@Preview
@Composable
fun CurrencyConverterScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CurrencyCard()
        HorizontalDivider(
            modifier = Modifier.padding(regular_padding),
            color = Color.Transparent,
        )
        AmountField()
    }
}