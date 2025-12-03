package com.github.carobatata.currencyconverter.domain

import com.github.carobatata.currencyconverter.data.CurrencyApi
import com.github.carobatata.currencyconverter.data.model.CurrencyResponse
import javax.inject.Inject

class CurrencyConverterRepository @Inject constructor(
    private val api: CurrencyApi
) {
    suspend fun getRates(base: String): Result<CurrencyResponse> {
        return try {
            val response = api.getRates(base)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Result.success(result)
            } else {
                Result.failure(Exception("API error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}