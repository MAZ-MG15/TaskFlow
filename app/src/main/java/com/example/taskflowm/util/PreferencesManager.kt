package com.example.taskflowm.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.PREFS_NAME)

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        val USER_TOKEN = stringPreferencesKey("user_token")
    }

    val userToken: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_TOKEN]
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN] = token
        }
    }

    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_TOKEN)
        }
    }
}
