package com.example.trendingtimesjetpack.data.data_sources.remote.login

import com.example.trendingtimesjetpack.data.dto.auth.LoginRequestDTO
import com.example.trendingtimesjetpack.data.dto.auth.LoginResponseDTO

interface AuthDataSource {
    suspend fun login(loginRequestDTO: LoginRequestDTO): LoginResponseDTO
}