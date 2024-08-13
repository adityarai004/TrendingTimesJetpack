package com.example.trendingtimesjetpack.core.di

import com.example.trendingtimesjetpack.core.networking.ApiService
import com.example.trendingtimesjetpack.data.data_sources.remote.login.AuthDataSource
import com.example.trendingtimesjetpack.data.repository.AuthRepositoryImpl
import com.example.trendingtimesjetpack.domain.repository.AuthRepository
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
        return AuthRepositoryImpl(authDataSource);
    }
}