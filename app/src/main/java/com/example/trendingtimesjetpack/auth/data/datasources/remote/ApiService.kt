package com.example.trendingtimesjetpack.auth.data.datasources.remote

import com.example.trendingtimesjetpack.core.utils.Resource

interface AuthService {
    suspend fun login(username: String, password: String): Resource<String>
}