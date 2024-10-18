package com.nicolascristaldo.worldcountries

import androidx.annotation.StringRes

enum class AppDestinations(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    Search(title = R.string.search_countries),
    Favorites(title = R.string.favorite_countries),
    CountryDetails(title = R.string.country_details)
}