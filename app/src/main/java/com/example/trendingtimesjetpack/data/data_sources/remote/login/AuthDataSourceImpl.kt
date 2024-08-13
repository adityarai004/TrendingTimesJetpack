package com.example.trendingtimesjetpack.data.data_sources.remote.login

import com.example.trendingtimesjetpack.core.networking.ApiService
import com.example.trendingtimesjetpack.data.dto.auth.LoginRequestDTO
import com.example.trendingtimesjetpack.data.dto.auth.LoginResponseDTO
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private val apiService: ApiService) : AuthDataSource {

    override suspend fun login(loginRequestDTO: LoginRequestDTO): LoginResponseDTO = apiService.login(loginRequestDTO)

}