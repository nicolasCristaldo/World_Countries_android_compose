package com.nicolascristaldo.worldcountries.ui.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.nicolascristaldo.worldcountries.R
import com.nicolascristaldo.worldcountries.ui.components.IconScreen
import com.nicolascristaldo.worldcountries.ui.screens.home.WorldCountriesViewModel

@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun SearchScreen(
    onClick: () -> Unit,
    viewModel: WorldCountriesViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        WorldCountriesSearchBar(
            viewModel = viewModel,
            onClick = { onClick() }
        )
        
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_size)))

        if(!viewModel.isSearchBarActive && viewModel.query.isEmpty()) {
            IconScreen(
                icon = R.drawable.ic_search_countries,
                title = R.string.search_any_country,
                modifier = Modifier.fillMaxSize()
            )
        }
        else if(!viewModel.isSearchBarActive) {
            DisabledSearchScreen(
                viewModel = viewModel,
                onClick = { onClick() }
            )
        }
    }
}

