package com.example.trendingtimesjetpack.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object LoginNavigation

@Serializable
object NewsNavigation

@Serializable
object SignUpNavigation

@Serializable
object ForgotPasswordNavigation

@Serializable
data class ReadNewsNavigation(val newsUrl: String)