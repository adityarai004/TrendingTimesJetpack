package com.example.trendingtimesjetpack.core.networking

import io.ktor.client.HttpClient
import io.ktor.client.request.post

class ApiService(private val client: HttpClient) {
    companion object {
        private const val END_POINT = "https://trendingtimesbackend.onrender.com/"
    }

    suspend fun login() = client.post(END_POINT)
}