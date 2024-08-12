package com.example.trendingtimesjetpack.data.data_sources.remote

import com.example.trendingtimesjetpack.core.utils.Resource

interface AuthService {
    suspend fun login(username: String, password: String): Resource<String>
}