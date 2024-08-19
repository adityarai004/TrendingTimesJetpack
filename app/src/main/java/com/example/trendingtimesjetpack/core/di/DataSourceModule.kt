package com.example.trendingtimesjetpack.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.trendingtimesjetpack.core.networking.AuthService
import com.example.trendingtimesjetpack.data.data_sources.local.prefs_data_source.PrefDataSource
import com.example.trendingtimesjetpack.data.data_sources.local.prefs_data_source.PrefsDataSourceImpl
import com.example.trendingtimesjetpack.data.data_sources.remote.auth.AuthDataSource
import com.example.trendingtimesjetpack.data.data_sources.remote.auth.AuthDataSourceImpl
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
    fun provideAuthDataSource(authService: AuthService): AuthDataSource =
        AuthDataSourceImpl(authService)

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