package com.nicolascristaldo.worldcountries.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.nicolascristaldo.worldcountries.R
import com.nicolascristaldo.worldcountries.model.Flag

@Composable
fun CountryFlag(
    flag: Flag,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = flag.imageUrl,
            contentDescription = flag.alt,
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.ic_loading),
            modifier = modifier
        )
    }
}