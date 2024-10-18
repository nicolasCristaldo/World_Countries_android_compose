package com.nicolascristaldo.worldcountries.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import com.nicolascristaldo.worldcountries.R
import com.nicolascristaldo.worldcountries.ui.screens.home.WorldCountriesViewModel

@Composable
fun BottomNavigationBar(
    viewModel: WorldCountriesViewModel,
    navController: NavHostController
) {
    NavigationBar(
        modifier = Modifier.height(dimensionResource(id = R.dimen.bottom_bar_size))
    ) {
        viewModel.navigationList.forEachIndexed { index, navigationItem ->
            NavigationBarItem(
                selected = index == viewModel.selectedItemIndex,
                onClick = {
                    viewModel.selectedItemIndex = index
                    navController.navigate(navigationItem.name)
                },
                icon = {
                    Icon(
                        if (viewModel.selectedItemIndex == index) navigationItem.selectedIcon
                        else navigationItem.unselectedIcon,
                        contentDescription = navigationItem.name
                    )
                }
            )
        }
    }
}