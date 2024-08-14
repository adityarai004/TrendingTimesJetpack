package com.example.trendingtimesjetpack.data.dto.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDTO(
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
)