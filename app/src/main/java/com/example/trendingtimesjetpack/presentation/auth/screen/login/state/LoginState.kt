package com.example.trendingtimesjetpack.presentation.auth.screen.login.state

import com.example.trendingtimesjetpack.core.ui.ErrorState

data class LoginState(
    val email: String = "",
    val password: String = "",
    val errorState: LoginErrorState = LoginErrorState(),
    val isLoginSuccessful: Boolean = false,
    val loginInProgress: Boolean = false,
    val isLoginError: Boolean = false,
    val loginErrorString: String = "",
)

data class LoginErrorState(
    val emailOrMobileErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState()
)