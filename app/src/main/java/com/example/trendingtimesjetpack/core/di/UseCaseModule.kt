package com.example.trendingtimesjetpack.core.di

import com.example.trendingtimesjetpack.domain.repository.AuthRepository
import com.example.trendingtimesjetpack.domain.repository.LocalPrefsRepository
import com.example.trendingtimesjetpack.domain.use_cases.GetBooleanUseCase
import com.example.trendingtimesjetpack.domain.use_cases.GetStringUseCase
import com.example.trendingtimesjetpack.domain.use_cases.GetUserAuthKeyUseCase
import com.example.trendingtimesjetpack.domain.use_cases.LoginUseCase
import com.example.trendingtimesjetpack.domain.use_cases.SetBooleanUseCase
import com.example.trendingtimesjetpack.domain.use_cases.SetStringUseCase
import com.example.trendingtimesjetpack.domain.use_cases.SetUserAuthTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase =
        LoginUseCase(authRepository)

    @Provides
    @Singleton
    fun provideGetUserAuthKeyUseCase(localPrefsRepository: LocalPrefsRepository): GetUserAuthKeyUseCase =
        GetUserAuthKeyUseCase(localPrefsRepository)

    @Provides
    @Singleton
    fun provideSetUserAuthKeyUseCase(localPrefsRepository: LocalPrefsRepository): SetUserAuthTokenUseCase =
        SetUserAuthTokenUseCase(localPrefsRepository)

    @Provides
    @Singleton
    fun provideGetBooleanUseCase(localPrefsRepository: LocalPrefsRepository): GetBooleanUseCase =
        GetBooleanUseCase(localPrefsRepository)

    @Provides
    @Singleton
    fun provideGetStringUseCase(localPrefsRepository: LocalPrefsRepository): GetStringUseCase =
        GetStringUseCase(localPrefsRepository)

    @Provides
    @Singleton
    fun provideSetBoolUseCase(localPrefsRepository: LocalPrefsRepository): SetBooleanUseCase =
        SetBooleanUseCase(localPrefsRepository)

    @Provides
    @Singleton
    fun provideSetStringUseCase(localPrefsRepository: LocalPrefsRepository): SetStringUseCase =
        SetStringUseCase(localPrefsRepository)
}