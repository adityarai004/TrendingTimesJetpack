package com.example.trendingtimesjetpack.core.networking

import com.example.trendingtimesjetpack.data.dto.auth.LoginRequestDTO
import com.example.trendingtimesjetpack.data.dto.auth.LoginResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class ApiService @Inject constructor(private val client: HttpClient) {
    companion object {
        private const val END_POINT = "https://trendingtimesbackend.onrender.com"
    }

    suspend fun login(loginRequestDTO: LoginRequestDTO): LoginResponseDTO {
        val response = client.post("${END_POINT}/auth/login") {
            setBody(
                loginRequestDTO
            )
        }.body<LoginResponseDTO>()
        return response
    }
}