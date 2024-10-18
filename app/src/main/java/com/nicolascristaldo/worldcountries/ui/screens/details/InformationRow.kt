package com.nicolascristaldo.worldcountries.ui.screens.details

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.nicolascristaldo.worldcountries.R

@Composable
fun InformationRow(
    type: String,
    information: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.horizontal_text_padding_size),
                vertical = dimensionResource(id = R.dimen.medium_size)
            )
    ) {
        Text(
            text = "$type: ",
            style = MaterialTheme.typography.bodySmall
        )
        Text(text = information)
    }
}

