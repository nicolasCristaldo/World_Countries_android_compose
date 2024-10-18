package com.nicolascristaldo.worldcountries.ui.screens.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.nicolascristaldo.worldcountries.data.StoreFavoriteCountries
import com.nicolascristaldo.worldcountries.ui.components.ErrorScreen
import com.nicolascristaldo.worldcountries.ui.components.LoadingScreen
import com.nicolascristaldo.worldcountries.ui.screens.home.WorldCountriesViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun CountryDetailsScreen(
    worldCountriesViewModel: WorldCountriesViewModel,
    detailsViewModel: CountryDetailsViewModel,
    retryAction: () -> Unit,
    scope: CoroutineScope,
    dataStore: StoreFavoriteCountries,
    favoriteCountries: State<String?>,
    modifier: Modifier = Modifier,
) {
    when(val countryDetailsUiState = detailsViewModel.countryDetailsUiState.collectAsState().value) {
        is CountryDetailsUiState.Success -> CountryInformationScreen(
            country = countryDetailsUiState.country.first(),
            viewModel = worldCountriesViewModel,
            scope = scope,
            dataStore = dataStore,
            favoriteCountries = favoriteCountries
        )
        is CountryDetailsUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is CountryDetailsUiState.Error -> ErrorScreen(
            retryAction = retryAction,
            modifier = Modifier.fillMaxSize()
        )
    }
}

