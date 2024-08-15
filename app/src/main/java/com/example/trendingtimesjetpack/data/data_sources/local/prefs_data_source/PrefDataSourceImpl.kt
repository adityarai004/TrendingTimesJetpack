package com.example.trendingtimesjetpack.data.data_sources.local.prefs_data_source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.trendingtimesjetpack.core.constants.LocalPrefsConstants
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class PrefsDataSourceImpl @Inject constructor(private val dataSource: DataStore<Preferences>) :
    PrefDataSource {
    override suspend fun setUserToken(userToken: String) {
        dataSource.edit { prefs ->
            prefs[stringPreferencesKey(LocalPrefsConstants.USER_TOKEN)] = userToken
            prefs[booleanPreferencesKey(LocalPrefsConstants.USER_IS_LOGGED_IN)] = true
        }
    }

    override suspend fun getUserToken(): String {
        val preferences = dataSource.data.first()
        return preferences[stringPreferencesKey(LocalPrefsConstants.USER_TOKEN)] ?: ""
    }

    override suspend fun getString(stringKey: String): String {
        val preferences = dataSource.data.first()
        return preferences[stringPreferencesKey(stringKey)] ?: ""

    }

    override suspend fun getBool(boolKey: String): Boolean {
        val preferences = dataSource.data.first()
        return preferences[booleanPreferencesKey(boolKey)] ?: false
    }

    override suspend fun setBool(key: String, value: Boolean) {
        dataSource.edit { prefs ->
            prefs[booleanPreferencesKey(key)] = value
        }
    }

    override suspend fun setString(key: String, value: String) {
        dataSource.edit { prefs ->
            prefs[stringPreferencesKey(key)] = value
        }
    }

}