package com.nicolascristaldo.worldcountries.ui.screens.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.nicolascristaldo.worldcountries.R
import com.nicolascristaldo.worldcountries.ui.components.CountriesGridScreen
import com.nicolascristaldo.worldcountries.ui.components.ErrorScreen
import com.nicolascristaldo.worldcountries.ui.components.IconScreen
import com.nicolascristaldo.worldcountries.ui.components.LoadingScreen
import com.nicolascristaldo.worldcountries.ui.screens.home.WorldCountriesUiState
import com.nicolascristaldo.worldcountries.ui.screens.home.WorldCountriesViewModel

@Composable
fun FavoriteCountriesScreen(
    viewModel: WorldCountriesViewModel,
    onClick: () -> Unit,
    favoriteCountries: State<String?>,
    modifier: Modifier = Modifier
) {
    when(viewModel.favoritesUiState) {
        is WorldCountriesUiState.Success -> CountriesGridScreen(
            countries = (viewModel.favoritesUiState as WorldCountriesUiState.Success).queryResponse,
            onClick = { onClick() },
            viewModel = viewModel
        )
        is WorldCountriesUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is WorldCountriesUiState.Error ->
            if ((viewModel.favoritesUiState as WorldCountriesUiState.Error).isIOError()) {
                ErrorScreen(
                    retryAction = { viewModel.getFavoriteCountries(favoriteCountries.value.toString()) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            else {
                IconScreen(
                    icon = R.drawable.ic_add_favorite,
                    title = R.string.no_favorites_found,
                    subtitle = R.string.add_favorites,
                    modifier = Modifier.fillMaxSize()
                )
            }
    }
}