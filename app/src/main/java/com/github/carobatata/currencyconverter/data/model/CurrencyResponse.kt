package com.github.carobatata.currencyconverter.data.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyResponse(
    @field:Json(name = "base")
    val base: String,

    @field:Json(name = "date")
    val date: String,

    @field:Json(name = "rates")
    val rates: Rates,

    @field:Json(name = "success")
    val success: Boolean,

    @field:Json(name = "timestamp")
    val timestamp: Int
)