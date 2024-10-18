package com.nicolascristaldo.worldcountries.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.nicolascristaldo.worldcountries.AppDestinations

data class NavigationItem(
    val name: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

object NavigationItemsProvider {
    fun getNavigationItems(): List<NavigationItem> {
        return listOf(
            NavigationItem(
                name = AppDestinations.Home.name,
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            ),
            NavigationItem(
                name = AppDestinations.Search.name,
                selectedIcon = Icons.Filled.Search,
                unselectedIcon = Icons.Outlined.Search
            ),
            NavigationItem(
                name = AppDestinations.Favorites.name,
                selectedIcon = Icons.Filled.Favorite,
                unselectedIcon = Icons.Outlined.FavoriteBorder
            )
        )
    }
}