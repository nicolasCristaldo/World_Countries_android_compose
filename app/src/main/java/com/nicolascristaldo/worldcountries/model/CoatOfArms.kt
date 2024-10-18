package com.nicolascristaldo.worldcountries.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoatOfArms(
    @SerialName(value = "png")
    val imageUrl: String?
)