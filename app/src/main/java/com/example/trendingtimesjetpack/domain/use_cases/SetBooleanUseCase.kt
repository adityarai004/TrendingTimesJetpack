package com.example.trendingtimesjetpack.domain.use_cases

import com.example.trendingtimesjetpack.domain.repository.LocalPrefsRepository
import javax.inject.Inject

class SetBooleanUseCase @Inject constructor(private val localPrefsRepository: LocalPrefsRepository) {
    suspend operator fun invoke(key: String, value: Boolean) = localPrefsRepository.setBool(key, value)
}