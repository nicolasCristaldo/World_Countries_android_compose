package com.nicolascristaldo.worldcountries.data

import com.nicolascristaldo.worldcountries.model.Country
import com.nicolascristaldo.worldcountries.network.WorldCountriesApiService

interface WorldCountriesRepository {
    suspend fun getAllCountries(): List<Country>

    suspend fun getFavoriteCountries(codes: String): List<Country>

    suspend fun getCountriesByName(name: String): List<Country>

    suspend fun getCountryById(id: String): List<Country>
}

class NetworkWorldCountriesRepository(
    private val worldCountriesApiService: WorldCountriesApiService
): WorldCountriesRepository {
    override suspend fun getAllCountries(): List<Country> =
        worldCountriesApiService.getAllCountries()

    override suspend fun getFavoriteCountries(codes: String): List<Country> =
        worldCountriesApiService.getFavoriteCountries(codes)

    override suspend fun getCountriesByName(name: String): List<Country> =
        worldCountriesApiService.getCountriesByName(name)

    override suspend fun getCountryById(id: String): List<Country> =
        worldCountriesApiService.getCountryById(id)
}