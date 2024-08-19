package com.example.trendingtimesjetpack.domain.use_cases

import com.example.trendingtimesjetpack.core.utils.Resource
import com.example.trendingtimesjetpack.data.dto.auth.AuthResponseDTO
import com.example.trendingtimesjetpack.data.dto.auth.SignUpRequestDTO
import com.example.trendingtimesjetpack.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(name: String, email: String, password: String, gender: String, dob: Long): Flow<Resource<AuthResponseDTO>> {
        val signUpRequestDTO = SignUpRequestDTO(name, email, password, gender, dob)
        return authRepository.signUp(signUpRequestDTO)
    }
}