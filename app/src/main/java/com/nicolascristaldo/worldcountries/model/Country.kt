package com.nicolascristaldo.worldcountries.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.floor

@Serializable
data class Country(
    @SerialName(value = "ccn3")
    val id: String = "",
    val area: Float,
    @SerialName(value = "capital")
    val capitals: List<String>?,
    val coatOfArms: CoatOfArms,
    val continents: List<String>,
    @SerialName(value = "flag")
    val emojiFlag: String,
    @SerialName(value = "flags")
    val flag: Flag,
    val independent: Boolean?,
    val name: Name,
    val population: Int,
    val region: String,
    val subregion: String?,
    val unMember: Boolean
) {
    fun getCapitals(): String {
        var result = "-"

        if (capitals != null) {
            result = ""
            for (capital in capitals) {
                result += "$capital, "
            }
            result = result.trimEnd(',', ' ')
        }

        return result
    }

    fun getContinents(): String {
        var result = ""
        for (continent in continents) {
            result += "$continent, "
        }
        result = result.trimEnd(',', ' ')
        return result
    }

    fun populationToString(): String {
        var result = when(population) {
            in 0..999 -> population.toString()
            in 1000..999999 -> "%.1fk".format((population / 1000f))
            else -> "%.1fM".format((population / 1000000f))
        }
        return result
    }
}