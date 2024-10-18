package com.nicolascristaldo.worldcountries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nicolascristaldo.worldcountries.ui.WorldCountriesApp
import com.nicolascristaldo.worldcountries.ui.theme.WorldCountriesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorldCountriesTheme{
                WorldCountriesApp()
            }
        }
    }
}