package com.nicolascristaldo.worldcountries.ui.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.nicolascristaldo.worldcountries.R
import com.nicolascristaldo.worldcountries.data.StoreFavoriteCountries
import com.nicolascristaldo.worldcountries.model.Country
import com.nicolascristaldo.worldcountries.ui.components.CountryFlag
import com.nicolascristaldo.worldcountries.ui.screens.home.WorldCountriesViewModel
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CountryInformationScreen(
    country: Country,
    viewModel: WorldCountriesViewModel,
    scope: CoroutineScope,
    dataStore: StoreFavoriteCountries,
    favoriteCountries: State<String?>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            CountryFlag(
                flag = country.flag,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.details_flag_size))
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_size)))
            Text(
                text = country.name.common,
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = country.name.official,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.spacer_size))
            )

            CoatOfArmsRow(country = country)

            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacer_size)))

            InformationRow(
                type = stringResource(id = R.string.capital),
                information = country.getCapitals()
            )
            InformationRow(
                type = stringResource(id = R.string.population),
                information = country.populationToString()
            )
            InformationRow(
                type = stringResource(id = R.string.continent),
                information = country.getContinents()
            )
            InformationRow(
                type = stringResource(id = R.string.region),
                information = country.region
            )
            country.subregion?.let {
                InformationRow(
                    type = stringResource(id = R.string.subregion),
                    information = it
                )
            }
            InformationRow(
                type = stringResource(id = R.string.area),
                information = country.area.toString()
            )
            InformationRow(
                type = stringResource(id = R.string.independent),
                information = country.independent.toString()
            )
            InformationRow(
                type = stringResource(id = R.string.un_member),
                information = country.unMember.toString()
            )
        }
        FabFavorites(
            country = country,
            scope = scope,
            favoriteCountries = favoriteCountries,
            viewModel = viewModel,
            dataStore = dataStore
        )
    }
}
