package com.example.trendingtimesjetpack.presentation.auth.screen.signup.state

import com.example.trendingtimesjetpack.core.ui.ErrorState

data class SignUpState(
    val email: String = "",
    val name: String = "",
    val gender: String = "",
    val dob: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isSignUpSuccessful: Boolean = false,
    val signUpInProgress: Boolean = false,
    val isSignUpError: Boolean = false,
    val signUpErrorString: String = "",
    val errorState: SignUpErrorState = SignUpErrorState(),
    val isDobDialogOpen: Boolean = false
)

data class SignUpErrorState(
    val emailErrorState: ErrorState = ErrorState(),
    val nameErrorState: ErrorState = ErrorState(),
    val genderErrorState: ErrorState = ErrorState(),
    val dobErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState(),
    val confirmPasswordErrorState: ErrorState = ErrorState()
)