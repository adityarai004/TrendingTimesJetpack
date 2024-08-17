package com.example.trendingtimesjetpack.presentation.auth

import com.example.trendingtimesjetpack.R
import com.example.trendingtimesjetpack.core.ui.ErrorState

val invalidEmailErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.invalid_email
)
val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_password
)
val invalidPasswordErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.invalid_password_error_message
)
val emailEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_email_mobile
)