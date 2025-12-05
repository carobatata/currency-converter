package com.github.carobatata.currencyconverter.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.SwapVerticalCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.github.carobatata.currencyconverter.ui.component.CurrencyCard
import com.github.carobatata.currencyconverter.ui.theme.app_blue
import com.github.carobatata.currencyconverter.ui.theme.regular_padding

@Composable
fun CurrencyConverterScreen(
    onClick: () -> Unit,
    viewModel: CurrencyConverterViewModel = hiltViewModel()
) {
    val fromCurrency by viewModel.fromCurrency.collectAsState()
    val toCurrency by viewModel.toCurrency.collectAsState()
    val amount by viewModel.amount.collectAsState()
    val convertedAmount by viewModel.convertedAmount.collectAsState()

    var amountText by rememberSaveable { mutableStateOf(if (amount == 0.0) "" else amount.toString()) }

    Column(
        modifier = Modifier
            .padding(regular_padding)
            .fillMaxSize()
    ) {
        CurrencyCard(onClick, fromCurrency)
        HorizontalDivider(
            modifier = Modifier.padding(regular_padding),
            color = Color.Transparent,
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = amountText,
            onValueChange = { newText ->
                amountText = newText
                val number = newText.toDoubleOrNull() ?: 0.0
                viewModel.setAmount(number)
            },
            trailingIcon = { Icon(Icons.Default.AttachMoney, contentDescription = null) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            )
        )

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
        CurrencyCard(onClick, toCurrency)
        HorizontalDivider(
            modifier = Modifier.padding(regular_padding),
            color = Color.Transparent,
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = convertedAmount.toString(),
            onValueChange = {},
            readOnly = true,
            trailingIcon = { Icon(Icons.Default.AttachMoney, contentDescription = null) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            )
        )
    }
}