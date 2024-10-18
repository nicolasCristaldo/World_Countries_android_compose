package com.nicolascristaldo.worldcountries.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.nicolascristaldo.worldcountries.R
import com.nicolascristaldo.worldcountries.model.Country
import com.nicolascristaldo.worldcountries.ui.screens.home.WorldCountriesViewModel

@Composable
fun CountriesGridScreen(
    countries: List<Country>,
    onClick: () -> Unit,
    viewModel: WorldCountriesViewModel,
    contentPadding: PaddingValues = PaddingValues(dimensionResource(id = R.dimen.no_size)),
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.grid_cell_size)),
        contentPadding = contentPadding
    ) {
        items(items = countries, key = { country -> country.id }) { country ->
            CountryCard(
                country = country,
                onClick = onClick,
                viewModel = viewModel,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.min_size))
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
    }
}