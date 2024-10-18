package com.nicolascristaldo.worldcountries

import android.app.Application
import com.nicolascristaldo.worldcountries.data.AppContainer
import com.nicolascristaldo.worldcountries.data.DefaultContainer

class WorldCountriesApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer()
    }
}