package com.example.trendingtimesjetpack.core.di

import com.example.trendingtimesjetpack.domain.repository.AuthRepository
import com.example.trendingtimesjetpack.domain.use_cases.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase =
        LoginUseCase(authRepository)
}