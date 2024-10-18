package com.nicolascristaldo.worldcountries.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.nicolascristaldo.worldcountries.R
import com.nicolascristaldo.worldcountries.model.Country
import com.nicolascristaldo.worldcountries.ui.screens.home.WorldCountriesViewModel

@Composable
fun CountrySearchColumn(
    countries: List<Country>,
    onClick: () -> Unit,
    viewModel: WorldCountriesViewModel,
) {
    LazyColumn {
        items(countries) { country ->
            Column {
                Text(
                    text = "${country.emojiFlag} ${country.name.common}",
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.horizontal_text_padding_size),
                            vertical = dimensionResource(id = R.dimen.medium_size)
                        )
                        .fillMaxWidth()
                        .clickable {
                            viewModel.selectedCountryId = country.id
                            onClick()
                        }
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}