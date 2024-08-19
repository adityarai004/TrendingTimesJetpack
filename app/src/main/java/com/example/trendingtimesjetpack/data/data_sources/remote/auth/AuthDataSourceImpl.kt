package com.example.trendingtimesjetpack.data.data_sources.remote.auth

import com.example.trendingtimesjetpack.core.networking.AuthService
import com.example.trendingtimesjetpack.data.dto.auth.LoginRequestDTO
import com.example.trendingtimesjetpack.data.dto.auth.AuthResponseDTO
import com.example.trendingtimesjetpack.data.dto.auth.SignUpRequestDTO
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private val authService: AuthService) : AuthDataSource {

    override suspend fun login(loginRequestDTO: LoginRequestDTO): AuthResponseDTO = authService.login(loginRequestDTO)

    override suspend fun signUp(signUpRequestDTO: SignUpRequestDTO): AuthResponseDTO = authService.signup(signUpRequestDTO)

}