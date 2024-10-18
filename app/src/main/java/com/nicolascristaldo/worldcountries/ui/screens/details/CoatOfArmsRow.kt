package com.nicolascristaldo.worldcountries.ui.screens.details

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.nicolascristaldo.worldcountries.R
import com.nicolascristaldo.worldcountries.model.Country

@Composable
fun CoatOfArmsRow(
    country: Country,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.coat_of_arms_row_size))
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.horizontal_text_padding_size),
                vertical = dimensionResource(id = R.dimen.medium_size)
            )
    ) {
        Text(
            text = stringResource(id = R.string.coat_of_arms) + ": ",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacer_size)))

        AsyncImage(
            model = country.coatOfArms.imageUrl,
            contentDescription = "coat of arms of " + country.name.common,
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.ic_loading),
            contentScale = ContentScale.Fit
        )
    }
}