package com.example.trendingtimesjetpack.presentation.auth.screen.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.trendingtimesjetpack.core.constants.RegexConstants
import com.example.trendingtimesjetpack.core.ui.ErrorState
import com.example.trendingtimesjetpack.presentation.auth.emailEmptyErrorState
import com.example.trendingtimesjetpack.presentation.auth.invalidEmailErrorState
import com.example.trendingtimesjetpack.presentation.auth.invalidPasswordErrorState
import com.example.trendingtimesjetpack.presentation.auth.passwordEmptyErrorState
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.state.SignUpState
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.state.SignUpUiEvent
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.state.emptyDobErrorState
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.state.emptyGenderErrorState
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.state.emptyNameErrorState
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.state.invalidNameErrorState
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.state.mismatchPasswordErrorState
import kotlin.math.sign

class SignUpViewModel : ViewModel() {
    var signUpState = mutableStateOf(SignUpState())
        private set

    fun onUiEvent(event: SignUpUiEvent) {
        when (event) {
            is SignUpUiEvent.NameChanged -> {
                signUpState.value = signUpState.value.copy(
                    name = event.newValue,
                    errorState = signUpState.value.errorState.copy(
                        nameErrorState = if (event.newValue.isEmpty())
                            emptyNameErrorState
                        else if (!event.newValue.matches(Regex(RegexConstants.NAME_REGEX)))
                            invalidNameErrorState
                        else
                            ErrorState()
                    )
                )
            }

            is SignUpUiEvent.EmailChanged -> {
                signUpState.value = signUpState.value.copy(
                    email = event.newEmailValue,
                    errorState = signUpState.value.errorState.copy(
                        emailErrorState = if (event.newEmailValue.isEmpty())
                            emailEmptyErrorState
                        else if (!event.newEmailValue.matches(Regex(RegexConstants.EMAIL_REGEX)))
                            invalidEmailErrorState
                        else
                            ErrorState()
                    )
                )
            }

            is SignUpUiEvent.PasswordChanged -> {
                signUpState.value = signUpState.value.copy(
                    password = event.newPasswordValue,
                    confirmPassword = signUpState.value.confirmPassword,
                    errorState = signUpState.value.errorState.copy(
                        passwordErrorState = if (event.newPasswordValue.isEmpty())
                            passwordEmptyErrorState
                        else if (!event.newPasswordValue.matches(Regex(RegexConstants.PASSWORD_REGEX)))
                            invalidPasswordErrorState
                        else
                            ErrorState()
                    )
                )

            }

            is SignUpUiEvent.ConfirmPasswordChanged -> {
                signUpState.value = signUpState.value.copy(
                    confirmPassword = event.newConfirmPasswordChanged,
                    password = signUpState.value.password,
                    errorState = signUpState.value.errorState.copy(
                        confirmPasswordErrorState = if (event.newConfirmPasswordChanged.isEmpty())
                            passwordEmptyErrorState
                        else if (event.newConfirmPasswordChanged != signUpState.value.password)
                            mismatchPasswordErrorState
                        else
                            ErrorState()
                    )
                )
            }

            is SignUpUiEvent.GenderSelected -> {
                signUpState.value = signUpState.value.copy(
                    gender = event.newGenderValue,
                    errorState = signUpState.value.errorState.copy(
                        genderErrorState = if (signUpState.value.gender.isEmpty())
                            emptyGenderErrorState
                        else
                            ErrorState()
                    )
                )
            }

            is SignUpUiEvent.SelectDob -> {
                signUpState.value = signUpState.value.copy(
                    dob = event.newDobValue,
                    errorState = signUpState.value.errorState.copy(
                        dobErrorState = if (signUpState.value.dob.isEmpty())
                            emptyDobErrorState
                        else
                            ErrorState()
                    )
                )
            }

            SignUpUiEvent.SignUpClick -> {
                signUpState.value = signUpState.value.copy(
                    signUpInProgress = true,
                    signUpErrorString = "",
                    isSignUpError = false
                )
            }

            SignUpUiEvent.DobClick -> {
                signUpState.value = signUpState.value.copy(
                    isDobDialogOpen = !signUpState.value.isDobDialogOpen
                )
            }
        }
    }
}