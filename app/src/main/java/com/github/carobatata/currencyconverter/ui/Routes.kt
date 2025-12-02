package com.github.carobatata.currencyconverter.ui

import kotlinx.serialization.Serializable

@Serializable
object ScreenCurrencyConverter

@Serializable
object ScreenChangeCurrency

@Serializable
data class ScreenExampleDataClass(
    val name: String?,
    val age: Int
)

