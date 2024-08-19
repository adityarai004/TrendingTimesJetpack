package com.example.trendingtimesjetpack.data.data_sources.remote.auth

import com.example.trendingtimesjetpack.data.dto.auth.LoginRequestDTO
import com.example.trendingtimesjetpack.data.dto.auth.AuthResponseDTO
import com.example.trendingtimesjetpack.data.dto.auth.SignUpRequestDTO

interface AuthDataSource {
    suspend fun login(loginRequestDTO: LoginRequestDTO): AuthResponseDTO

    suspend fun signUp(signUpRequestDTO: SignUpRequestDTO): AuthResponseDTO
}