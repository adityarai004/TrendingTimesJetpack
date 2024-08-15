package com.example.trendingtimesjetpack.data.repository

import com.example.trendingtimesjetpack.data.data_sources.local.prefs_data_source.PrefDataSource
import com.example.trendingtimesjetpack.domain.repository.LocalPrefsRepository
import javax.inject.Inject

class LocalPrefsRepositoryImpl @Inject constructor(private val localPrefsDataSource: PrefDataSource) :
    LocalPrefsRepository {
    override suspend fun getBoolean(key: String): Boolean = localPrefsDataSource.getBool(key)
    override suspend fun getString(key: String): String = localPrefsDataSource.getString(key)
    override suspend fun setString(key: String, value: String) = localPrefsDataSource.setString(key,value)
    override suspend fun setBool(key: String, value: Boolean) = localPrefsDataSource.setBool(key, value)
    override suspend fun getUserAuthKey(): String = localPrefsDataSource.getUserToken()
    override suspend fun setUserAuthKey(authKey: String) = localPrefsDataSource.setUserToken(authKey)
}