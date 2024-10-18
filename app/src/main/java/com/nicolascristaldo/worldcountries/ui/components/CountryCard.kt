package com.nicolascristaldo.worldcountries.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.zIndex
import com.nicolascristaldo.worldcountries.R
import com.nicolascristaldo.worldcountries.model.Country
import com.nicolascristaldo.worldcountries.ui.screens.home.WorldCountriesViewModel

@Composable
fun CountryCard(
    country: Country,
    onClick: () -> Unit,
    viewModel: WorldCountriesViewModel,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.no_size)),
        onClick = {
            viewModel.selectedCountryId = country.id
            onClick()
        },
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            CountryFlag(
                flag = country.flag,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
            ) {
                Text(
                    text = country.name.common,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .zIndex(1f)
                        .align(Alignment.Center)
                )
            }
        }
    }
}