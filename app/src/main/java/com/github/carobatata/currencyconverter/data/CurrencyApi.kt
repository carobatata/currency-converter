package com.github.carobatata.currencyconverter.data

import com.github.carobatata.currencyconverter.data.model.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApi {

    @GET("latest/{base}")
    suspend fun getRates(
        @Path("base") base: String
    ): Response<CurrencyResponse>
}