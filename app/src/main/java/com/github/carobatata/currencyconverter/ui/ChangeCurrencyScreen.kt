package com.github.carobatata.currencyconverter.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.carobatata.currencyconverter.ui.component.CurrencyRow
import com.github.carobatata.currencyconverter.ui.theme.regular_padding
import com.github.carobatata.currencyconverter.ui.theme.rounded_corner

@Preview
@Composable
fun ChangeCurrencyScreen() {
    var query by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(regular_padding)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(regular_padding),
            shape = RoundedCornerShape(rounded_corner),
        )

        LazyColumn {
            items(3){
                CurrencyRow()
                HorizontalDivider(Modifier.padding(horizontal = regular_padding))
            }
        }
    }
}