package com.example.trendingtimesjetpack.core.di

import com.example.trendingtimesjetpack.MainViewModel
import com.example.trendingtimesjetpack.domain.use_cases.GetBooleanUseCase
import com.example.trendingtimesjetpack.domain.use_cases.GetUserAuthKeyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    @Singleton
    fun provideMainViewModel(getBooleanUseCase: GetBooleanUseCase,getUserAuthKeyUseCase: GetUserAuthKeyUseCase) : MainViewModel = MainViewModel(getBooleanUseCase,getUserAuthKeyUseCase)
}