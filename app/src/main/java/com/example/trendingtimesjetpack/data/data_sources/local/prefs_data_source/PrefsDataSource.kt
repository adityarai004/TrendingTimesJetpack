package com.example.trendingtimesjetpack.data.data_sources.local.prefs_data_source

interface PrefDataSource {
    suspend fun setUserToken(userToken: String)
    suspend fun getUserToken(): String
    suspend fun getString(stringKey: String): String
    suspend fun getBool(boolKey: String): Boolean
    suspend fun setBool(key: String, value: Boolean)
    suspend fun setString(key: String, value: String)
}