package com.nicolascristaldo.worldcountries.ui.screens.home

import com.nicolascristaldo.worldcountries.model.Country

sealed interface WorldCountriesUiState {
    data class Success(
        val queryResponse: List<Country>
    ): WorldCountriesUiState

    data class Error(
        val internetError: Boolean = false,
        val httpError: Boolean = false
    ): WorldCountriesUiState {
        fun isIOError() = internetError
        fun isHttpError() = httpError
    }

    object Loading: WorldCountriesUiState
}