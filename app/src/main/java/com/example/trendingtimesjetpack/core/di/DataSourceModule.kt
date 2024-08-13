package com.example.trendingtimesjetpack.core.di

import com.example.trendingtimesjetpack.core.networking.ApiService
import com.example.trendingtimesjetpack.data.data_sources.remote.login.AuthDataSource
import com.example.trendingtimesjetpack.data.data_sources.remote.login.AuthDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun provideAuthDataSource(apiService: ApiService): AuthDataSource = AuthDataSourceImpl(apiService)
}