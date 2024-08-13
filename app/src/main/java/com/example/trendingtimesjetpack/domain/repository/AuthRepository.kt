package com.example.trendingtimesjetpack.domain.repository

import com.example.trendingtimesjetpack.core.utils.Resource
import com.example.trendingtimesjetpack.data.dto.auth.LoginRequestDTO
import com.example.trendingtimesjetpack.data.dto.auth.LoginResponseDTO
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(loginRequestDTO: LoginRequestDTO): Flow<Resource<LoginResponseDTO>>
}