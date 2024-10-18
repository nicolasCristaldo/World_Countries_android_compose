package com.nicolascristaldo.worldcountries.ui.screens.details

import com.nicolascristaldo.worldcountries.model.Country

sealed interface CountryDetailsUiState {
    data class Success(val country: List<Country>): CountryDetailsUiState
    object Error: CountryDetailsUiState
    object Loading: CountryDetailsUiState
}