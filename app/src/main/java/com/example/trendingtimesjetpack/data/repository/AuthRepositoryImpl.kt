package com.example.trendingtimesjetpack.data.repository

import com.example.trendingtimesjetpack.core.utils.Resource
import com.example.trendingtimesjetpack.data.data_sources.remote.auth.AuthDataSource
import com.example.trendingtimesjetpack.data.dto.auth.LoginRequestDTO
import com.example.trendingtimesjetpack.data.dto.auth.AuthResponseDTO
import com.example.trendingtimesjetpack.data.dto.auth.SignUpRequestDTO
import com.example.trendingtimesjetpack.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authDataSource: AuthDataSource): AuthRepository {
    override suspend fun login(loginRequestDTO: LoginRequestDTO): Flow<Resource<AuthResponseDTO>> = flow{
        emit(Resource.Loading)
        try {
            val response = authDataSource.login(loginRequestDTO)
            emit(Resource.Success(response))
        }catch (e: Exception){
            emit(Resource.Error(e.message ?: "An error occurred while login"))
        }
    }

    override suspend fun signUp(signUpRequestDTO: SignUpRequestDTO): Flow<Resource<AuthResponseDTO>> = flow {
        emit(Resource.Loading)
        try {
            val response = authDataSource.signUp(signUpRequestDTO)
            emit(Resource.Success(response))
        } catch (e: Exception){
            emit(Resource.Error(e.message ?: "An error occurred while sign up"))
        }
    }
}