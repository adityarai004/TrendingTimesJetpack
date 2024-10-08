package com.example.trendingtimesjetpack.core.di

import com.example.trendingtimesjetpack.core.networking.NewsService
import com.example.trendingtimesjetpack.data.data_sources.local.prefs_data_source.PrefDataSource
import com.example.trendingtimesjetpack.data.data_sources.remote.auth.AuthDataSource
import com.example.trendingtimesjetpack.data.repository.AuthRepositoryImpl
import com.example.trendingtimesjetpack.data.repository.LocalPrefsRepositoryImpl
import com.example.trendingtimesjetpack.domain.repository.AuthRepository
import com.example.trendingtimesjetpack.domain.repository.LocalPrefsRepository
import com.example.trendingtimesjetpack.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(authDataSource: AuthDataSource): AuthRepository{
        return AuthRepositoryImpl(authDataSource)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(prefDataSource: PrefDataSource): LocalPrefsRepository{
        return LocalPrefsRepositoryImpl(prefDataSource)
    }
}