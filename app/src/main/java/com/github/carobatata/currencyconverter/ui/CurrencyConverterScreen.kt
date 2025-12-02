package com.github.carobatata.currencyconverter.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVerticalCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.carobatata.currencyconverter.ui.component.AmountField
import com.github.carobatata.currencyconverter.ui.component.CurrencyCard
import com.github.carobatata.currencyconverter.ui.theme.app_blue
import com.github.carobatata.currencyconverter.ui.theme.regular_padding

@Composable
fun CurrencyConverterScreen(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(regular_padding)
            .fillMaxSize()
    ) {
        CurrencyCard(onClick)
        HorizontalDivider(
            modifier = Modifier.padding(regular_padding),
            color = Color.Transparent,
        )
        AmountField()
        HorizontalDivider(
            modifier = Modifier.padding(regular_padding),
            color = Color.Transparent,
        )
        Icon(
            imageVector = Icons.Filled.SwapVerticalCircle,
            modifier = Modifier.size(48.dp),
            contentDescription = null,
            tint = app_blue
        )
        HorizontalDivider(
            modifier = Modifier.padding(regular_padding),
            color = Color.Transparent,
        )
        CurrencyCard(onClick)
        HorizontalDivider(
            modifier = Modifier.padding(regular_padding),
            color = Color.Transparent,
        )
        AmountField()
    }
}