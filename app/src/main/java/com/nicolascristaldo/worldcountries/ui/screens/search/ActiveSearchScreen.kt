package com.nicolascristaldo.worldcountries.ui.screens.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nicolascristaldo.worldcountries.R
import com.nicolascristaldo.worldcountries.ui.components.ErrorScreen
import com.nicolascristaldo.worldcountries.ui.components.IconScreen
import com.nicolascristaldo.worldcountries.ui.components.LoadingScreen
import com.nicolascristaldo.worldcountries.ui.screens.home.WorldCountriesUiState
import com.nicolascristaldo.worldcountries.ui.screens.home.WorldCountriesViewModel

@Composable
fun ActiveSearchScreen(
    viewModel: WorldCountriesViewModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when(viewModel.searchUiState) {
        is WorldCountriesUiState.Success -> CountrySearchColumn(
            countries = (viewModel.searchUiState as WorldCountriesUiState.Success).queryResponse,
            onClick = { onClick() },
            viewModel = viewModel
        )
        is WorldCountriesUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is WorldCountriesUiState.Error ->
            if ((viewModel.searchUiState as WorldCountriesUiState.Error).isIOError()) {
                ErrorScreen(
                    retryAction = viewModel::getSearchedCountries,
                    modifier = Modifier.fillMaxSize()
                )
            }
            else {
                IconScreen(
                    icon = R.drawable.ic_unknown_flag,
                    title = R.string.no_countries_found,
                    modifier = Modifier.fillMaxSize()
                )
            }
    }
}