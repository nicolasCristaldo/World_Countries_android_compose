package com.nicolascristaldo.worldcountries.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.nicolascristaldo.worldcountries.R

@Composable
fun IconScreen(
    icon: Int,
    title: Int? = null,
    subtitle: Int? = null,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.height(dimensionResource(id = R.dimen.screen_icon_size))
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_size)))

        if (title != null) {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        if (subtitle != null) {
            Text(
                text = stringResource(id = subtitle),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}