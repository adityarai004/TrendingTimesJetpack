package com.example.trendingtimesjetpack.presentation.auth.screen.signup.state

import com.example.trendingtimesjetpack.R
import com.example.trendingtimesjetpack.core.ui.ErrorState

val emptyNameErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.signup_error_msg_empty_name
)

val invalidNameErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.signup_invalid_msg_empty_name
)

val mismatchPasswordErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.signup_error_msg_mismatch_password
)

val emptyGenderErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.signup_error_msg_empty_gender
)

val emptyDobErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.signup_error_msg_empty_dob
)

val invalidDobErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.signup_error_msg_invalid_dob
)