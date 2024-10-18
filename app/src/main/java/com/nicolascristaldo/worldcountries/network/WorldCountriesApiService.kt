package com.nicolascristaldo.worldcountries.network

import com.nicolascristaldo.worldcountries.model.Country
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WorldCountriesApiService {

    @GET("independent?status=true")
    suspend fun getAllCountries(): List<Country>

    @GET("alpha?")
    suspend fun getFavoriteCountries(@Query("codes") codes: String): List<Country>

    @GET("name/{name}")
    suspend fun getCountriesByName(@Path("name") name: String): List<Country>

    @GET("alpha/{id}")
    suspend fun getCountryById(@Path("id") id: String): List<Country>
}