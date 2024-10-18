package com.nicolascristaldo.worldcountries.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.res.stringResource
import com.nicolascristaldo.worldcountries.AppDestinations
import com.nicolascristaldo.worldcountries.R
import com.nicolascristaldo.worldcountries.ui.screens.home.WorldCountriesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorldCountriesTopAppBar(
    viewModel: WorldCountriesViewModel,
    currentScreen: AppDestinations,
    favoriteCountries:  State<String?>,
    navigateUp: () -> Unit
) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = currentScreen.title),
                style = MaterialTheme.typography.displayLarge
            )
        },
        navigationIcon = {
            if(currentScreen.title == AppDestinations.CountryDetails.title) {
                IconButton(
                    onClick = {
                        if (!favoriteCountries.value.isNullOrEmpty())
                            viewModel.getFavoriteCountries(favoriteCountries.value.toString())
                        navigateUp()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = stringResource(id = R.string.arrow_back)
                    )
                }
            }
        }
    )
}