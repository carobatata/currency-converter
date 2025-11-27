package com.github.carobatata.currencyconverter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.carobatata.currencyconverter.ui.theme.currency_card_padding
import com.github.carobatata.currencyconverter.ui.theme.currency_text_padding_start

@Composable
fun CurrencyCard() {
    OutlinedCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(currency_card_padding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                )
                Column(
                    modifier = Modifier.padding(start = currency_text_padding_start)
                ) {
                    Text("USD")
                    Text("United Stated Dollars")
                }
            }
            Icon(
                imageVector = Icons.Outlined.ArrowDropDown,
                contentDescription = null
            )
        }

    }
}