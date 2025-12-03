package com.github.carobatata.currencyconverter.data

import com.github.carobatata.currencyconverter.data.model.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("v1/latest?access_key=71c636ba26e9a52a2bae23cdb6023ff4")
    suspend fun getRates(
        @Query("base") base: String
    ) : Response<CurrencyResponse>
}