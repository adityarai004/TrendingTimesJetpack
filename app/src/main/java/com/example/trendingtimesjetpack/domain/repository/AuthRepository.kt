package com.example.trendingtimesjetpack.domain.repository

import com.example.trendingtimesjetpack.core.utils.Resource
import com.example.trendingtimesjetpack.data.dto.auth.LoginRequestDTO
import com.example.trendingtimesjetpack.data.dto.auth.AuthResponseDTO
import com.example.trendingtimesjetpack.data.dto.auth.SignUpRequestDTO
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(loginRequestDTO: LoginRequestDTO): Flow<Resource<AuthResponseDTO>>
    suspend fun signUp(signUpRequestDTO: SignUpRequestDTO): Flow<Resource<AuthResponseDTO>>
}