package com.nicolascristaldo.worldcountries.ui.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.nicolascristaldo.worldcountries.R
import com.nicolascristaldo.worldcountries.ui.components.CountriesGridScreen
import com.nicolascristaldo.worldcountries.ui.components.ErrorScreen
import com.nicolascristaldo.worldcountries.ui.components.LoadingScreen

@Composable
fun HomeScreen(
    worldCountriesUiState: WorldCountriesUiState,
    viewModel: WorldCountriesViewModel,
    onClick: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(dimensionResource(id = R.dimen.no_size)),
    modifier: Modifier = Modifier
) {
    when(worldCountriesUiState) {
        is WorldCountriesUiState.Success -> CountriesGridScreen(
            countries = worldCountriesUiState.queryResponse,
            onClick = onClick,
            viewModel = viewModel,
            contentPadding = contentPadding
        )
        is WorldCountriesUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is WorldCountriesUiState.Error -> ErrorScreen(
            retryAction = viewModel::getCountries,
            modifier = Modifier.fillMaxSize()
        )
    }
}