package com.example.trendingtimesjetpack.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.trendingtimesjetpack.core.networking.ApiService
import com.example.trendingtimesjetpack.data.data_sources.local.prefs_data_source.PrefDataSource
import com.example.trendingtimesjetpack.data.data_sources.local.prefs_data_source.PrefsDataSourceImpl
import com.example.trendingtimesjetpack.data.data_sources.remote.login.AuthDataSource
import com.example.trendingtimesjetpack.data.data_sources.remote.login.AuthDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideAuthDataSource(apiService: ApiService): AuthDataSource =
        AuthDataSourceImpl(apiService)

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("user_prefs")
        }
    }

    @Provides
    @Singleton
    fun provideDataStoreDataStore(dataStore: DataStore<Preferences>): PrefDataSource {
        return PrefsDataSourceImpl(dataStore)
    }
}