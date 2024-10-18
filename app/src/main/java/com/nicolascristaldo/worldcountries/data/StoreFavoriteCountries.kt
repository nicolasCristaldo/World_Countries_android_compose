package com.nicolascristaldo.worldcountries.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreFavoriteCountries(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("favorites")
        val FAVORITE_IDS_KEY = stringPreferencesKey("favorite_ids")
    }

    val getFavoriteIds: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[FAVORITE_IDS_KEY] ?: ""
        }

    suspend fun saveId(id: String) {
        context.dataStore.edit { preferences ->
            preferences[FAVORITE_IDS_KEY] = id
        }
    }
}