package com.nicolascristaldo.worldcountries.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nicolascristaldo.worldcountries.WorldCountriesApplication
import com.nicolascristaldo.worldcountries.data.WorldCountriesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CountryDetailsViewModel(private val worldCountriesRepository: WorldCountriesRepository): ViewModel() {
    private val _countryDetailsUiState = MutableStateFlow<CountryDetailsUiState>(CountryDetailsUiState.Loading)
    val countryDetailsUiState = _countryDetailsUiState.asStateFlow()

    fun getCountry(id: String) {
        viewModelScope.launch {
            _countryDetailsUiState.value = CountryDetailsUiState.Loading
            _countryDetailsUiState.value = try {
                CountryDetailsUiState.Success(worldCountriesRepository.getCountryById(id))
            }
            catch(e: IOException) {
                CountryDetailsUiState.Error
            }
            catch (e: HttpException) {
                CountryDetailsUiState.Error
            }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WorldCountriesApplication)
                val worldCountriesRepository = application.container.worldCountriesRepository
                CountryDetailsViewModel(worldCountriesRepository = worldCountriesRepository)
            }
        }
    }

}