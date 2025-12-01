package com.github.carobatata.currencyconverter.ui

import kotlinx.serialization.Serializable

@Serializable
object ScreenCurrencyConverter

@Serializable
data class ScreenExampleDataClass(
    val name: String?,
    val age: Int
)

@Serializable
object ScreenChangeCurrency
