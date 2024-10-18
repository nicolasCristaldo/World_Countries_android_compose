package com.nicolascristaldo.worldcountries.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nicolascristaldo.worldcountries.network.WorldCountriesApiService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class DefaultContainer: AppContainer {
    private val baseUrl = "https://restcountries.com/v3.1/"

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: WorldCountriesApiService by lazy {
        retrofit.create(WorldCountriesApiService::class.java)
    }

    override val worldCountriesRepository by lazy {
        NetworkWorldCountriesRepository(retrofitService)
    }
}