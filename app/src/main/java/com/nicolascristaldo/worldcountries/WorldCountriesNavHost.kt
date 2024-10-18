package com.nicolascristaldo.worldcountries

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nicolascristaldo.worldcountries.data.StoreFavoriteCountries
import com.nicolascristaldo.worldcountries.ui.screens.details.CountryDetailsScreen
import com.nicolascristaldo.worldcountries.ui.screens.details.CountryDetailsViewModel
import com.nicolascristaldo.worldcountries.ui.screens.favorites.FavoriteCountriesScreen
import com.nicolascristaldo.worldcountries.ui.screens.home.HomeScreen
import com.nicolascristaldo.worldcountries.ui.screens.home.WorldCountriesViewModel
import com.nicolascristaldo.worldcountries.ui.screens.search.SearchScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun WorldCountriesNavHost(
    viewModel: WorldCountriesViewModel,
    navController: NavHostController,
    favoriteCountries: State<String?>,
    scope: CoroutineScope,
    dataStore: StoreFavoriteCountries,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.Home.name,
        modifier = modifier
    ) {
        composable(route = AppDestinations.Home.name) {
            HomeScreen(
                viewModel = viewModel,
                worldCountriesUiState = viewModel.worldCountriesUiState,
                onClick = {
                    navController.navigate(AppDestinations.CountryDetails.name)
                }
            )
        }

        composable(route = AppDestinations.Search.name) {
            viewModel.getSearchedCountries()
            SearchScreen(
                viewModel = viewModel,
                onClick = {
                    navController.navigate(AppDestinations.CountryDetails.name)
                }
            )
        }

        composable(route = AppDestinations.Favorites.name) {
            viewModel.getFavoriteCountries(favoriteCountries.value.toString())
            FavoriteCountriesScreen(
                viewModel = viewModel,
                favoriteCountries = favoriteCountries,
                onClick = {
                    navController.navigate(AppDestinations.CountryDetails.name)
                }
            )
        }

        composable(route = AppDestinations.CountryDetails.name) {
            val countryDetailsViewModel: CountryDetailsViewModel = viewModel(factory = CountryDetailsViewModel.factory)
            countryDetailsViewModel.getCountry(viewModel.selectedCountryId)

            CountryDetailsScreen(
                detailsViewModel = countryDetailsViewModel,
                worldCountriesViewModel = viewModel,
                retryAction = { countryDetailsViewModel.getCountry(viewModel.selectedCountryId) },
                scope = scope,
                dataStore = dataStore,
                favoriteCountries = favoriteCountries
            )
        }
    }
}