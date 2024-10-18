package com.nicolascristaldo.worldcountries.ui.screens.details

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.nicolascristaldo.worldcountries.R
import com.nicolascristaldo.worldcountries.data.StoreFavoriteCountries
import com.nicolascristaldo.worldcountries.model.Country
import com.nicolascristaldo.worldcountries.ui.screens.home.WorldCountriesViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun FabFavorites(
    country: Country,
    scope: CoroutineScope,
    favoriteCountries: State<String?>,
    viewModel: WorldCountriesViewModel,
    dataStore: StoreFavoriteCountries,
    modifier: Modifier = Modifier
) {
    val ctx = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            onClick = {
                if (viewModel.isFavorite(favoriteCountries, country.id)) {
                    viewModel.removeFavorite(
                        scope = scope,
                        dataStore = dataStore,
                        id = country.id,
                        favoriteCountries = favoriteCountries
                    )
                    Toast.makeText(
                        ctx,
                        "${country.name.common} has been removed from favorites",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else {
                    viewModel.addFavorite(
                        scope = scope,
                        dataStore = dataStore,
                        id = country.id,
                        favoriteCountries = favoriteCountries
                    )
                    Toast.makeText(
                        ctx,
                        "${country.name.common} has been added to favorites",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.medium_size))
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = if (viewModel.isFavorite(favoriteCountries, country.id)) Icons.Filled.Favorite
                else Icons.Outlined.FavoriteBorder,
                contentDescription = if (viewModel.isFavorite(favoriteCountries, country.id))
                    stringResource(id = R.string.remove_favorite)
                else stringResource(id = R.string.add_favorite)
            )
        }
    }
}