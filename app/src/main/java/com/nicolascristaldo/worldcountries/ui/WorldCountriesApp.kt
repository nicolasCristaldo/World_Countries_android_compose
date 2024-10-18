package com.nicolascristaldo.worldcountries.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nicolascristaldo.worldcountries.AppDestinations
import com.nicolascristaldo.worldcountries.WorldCountriesNavHost
import com.nicolascristaldo.worldcountries.data.StoreFavoriteCountries
import com.nicolascristaldo.worldcountries.ui.components.BottomNavigationBar
import com.nicolascristaldo.worldcountries.ui.components.WorldCountriesTopAppBar
import com.nicolascristaldo.worldcountries.ui.screens.home.WorldCountriesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WorldCountriesApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppDestinations.valueOf(
        backStackEntry?.destination?.route ?: AppDestinations.Home.name
    )
    val viewModel: WorldCountriesViewModel = viewModel(factory = WorldCountriesViewModel.Factory)

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreFavoriteCountries(context)
    val favoriteCountries = dataStore.getFavoriteIds.collectAsState(initial = "")

    Scaffold(
        topBar = {
            WorldCountriesTopAppBar(
                viewModel = viewModel,
                currentScreen = currentScreen,
                favoriteCountries = favoriteCountries,
                navigateUp = { navController.navigateUp() }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                viewModel = viewModel
            )
        },
    ) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            WorldCountriesNavHost(
                navController = navController,
                favoriteCountries = favoriteCountries,
                viewModel = viewModel,
                scope = scope,
                dataStore = dataStore
            )
        }
    }
}