package com.nicolascristaldo.worldcountries.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nicolascristaldo.worldcountries.WorldCountriesApplication
import com.nicolascristaldo.worldcountries.data.NavigationItemsProvider
import com.nicolascristaldo.worldcountries.data.StoreFavoriteCountries
import com.nicolascristaldo.worldcountries.data.WorldCountriesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


@SuppressLint("MutableCollectionMutableState")
class WorldCountriesViewModel(
    private val worldCountriesRepository: WorldCountriesRepository
): ViewModel() {
    var worldCountriesUiState: WorldCountriesUiState by mutableStateOf(WorldCountriesUiState.Loading)
        private set

    var favoritesUiState: WorldCountriesUiState by mutableStateOf(WorldCountriesUiState.Loading)
        private set

    var searchUiState: WorldCountriesUiState by mutableStateOf(WorldCountriesUiState.Loading)
        private set

    var selectedCountryId by mutableStateOf("")

    var selectedItemIndex by mutableIntStateOf(0)
    val navigationList = NavigationItemsProvider.getNavigationItems()

    init {
        getCountries()
    }

    fun getCountries() {
        viewModelScope.launch {
            worldCountriesUiState = WorldCountriesUiState.Loading
            worldCountriesUiState = try {
                WorldCountriesUiState.Success(worldCountriesRepository.getAllCountries())
            }
            catch(e: IOException) {
                WorldCountriesUiState.Error(internetError = true)
            }
            catch(e: HttpException) {
                WorldCountriesUiState.Error(httpError = true)
            }
        }
    }

    // Favorites logic

    fun getFavoriteCountries(favoriteCountries: String) {
        viewModelScope.launch {
            favoritesUiState = WorldCountriesUiState.Loading
            favoritesUiState = try {
                WorldCountriesUiState.Success(
                    worldCountriesRepository.getFavoriteCountries(favoriteCountries)
                )
            }
            catch(e: IOException) {
                WorldCountriesUiState.Error(internetError = true)
            }
            catch(e: HttpException) {
                WorldCountriesUiState.Error(httpError = true)
            }
        }
    }

    fun isFavorite(
        favoriteCountries: State<String?>,
        id: String
    ): Boolean {
        var isFavorite = false

        if (favoriteCountries.value?.contains(id) == true) {
            isFavorite = true
        }
        return isFavorite
    }

    fun addFavorite(
        scope: CoroutineScope,
        dataStore: StoreFavoriteCountries,
        id: String,
        favoriteCountries: State<String?>
    ) {
        scope.launch {
            dataStore.saveId("$id,${favoriteCountries.value}")
        }
    }

    fun removeFavorite(
        scope: CoroutineScope,
        dataStore: StoreFavoriteCountries,
        id: String,
        favoriteCountries: State<String?>
    ) {
        var favoriteIds = favoriteCountries.value.toString()
        favoriteIds = favoriteIds.replace("$id,", "")
        scope.launch {
            dataStore.saveId(favoriteIds)
        }
    }
    // end Favorites logic

    // Search logic

    var query: String by mutableStateOf("")
    var isSearchBarActive by mutableStateOf(false)

    fun getSearchedCountries() {
        viewModelScope.launch {
            searchUiState = WorldCountriesUiState.Loading
            searchUiState = try {
                WorldCountriesUiState.Success(
                    worldCountriesRepository.getCountriesByName(query.trim('.', ' '))
                )
            }
            catch(e: IOException) {
                WorldCountriesUiState.Error(internetError = true)
            }
            catch(e: HttpException) {
                WorldCountriesUiState.Error(httpError = true)
            }
        }
    }

    // end Search logic

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WorldCountriesApplication)
                val worldCountriesRepository = application.container.worldCountriesRepository
                WorldCountriesViewModel(
                    worldCountriesRepository = worldCountriesRepository
                )
            }
        }
    }
}
