package com.example.trendingtimesjetpack.domain.use_cases

import com.example.trendingtimesjetpack.data.dto.auth.LoginRequestDTO
import com.example.trendingtimesjetpack.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String) = authRepository.login(LoginRequestDTO(username, password))
}