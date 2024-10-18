package com.nicolascristaldo.worldcountries.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nicolascristaldo.worldcountries.R
import com.nicolascristaldo.worldcountries.ui.screens.home.WorldCountriesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorldCountriesSearchBar(
    viewModel: WorldCountriesViewModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        colors = SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.background),
        query = viewModel.query,
        onQueryChange = {
            viewModel.query = it
            viewModel.getSearchedCountries()
        },
        onSearch = {
            viewModel.getSearchedCountries()
            viewModel.isSearchBarActive = false
        },
        active = viewModel.isSearchBarActive,
        onActiveChange = { viewModel.isSearchBarActive = it },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_placeholder),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        leadingIcon = {
            if(viewModel.isSearchBarActive) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = stringResource(id = R.string.arrow_back),
                    modifier = Modifier.clickable { viewModel.isSearchBarActive = false }
                )
            }
            else {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(id = R.string.search)
                )
            }
        },
        trailingIcon = {
            if(viewModel.query.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = stringResource(id = R.string.delete),
                    modifier = Modifier.clickable {
                        viewModel.query = ""
                    }
                )
            }
        },
        shape = SearchBarDefaults.inputFieldShape
    ){
        ActiveSearchScreen(
            viewModel = viewModel,
            onClick = { onClick() }
        )
    }
}