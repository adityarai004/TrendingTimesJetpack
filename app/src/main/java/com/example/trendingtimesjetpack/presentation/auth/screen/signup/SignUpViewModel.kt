package com.example.trendingtimesjetpack.presentation.auth.screen.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendingtimesjetpack.core.constants.RegexConstants
import com.example.trendingtimesjetpack.core.ui.ErrorState
import com.example.trendingtimesjetpack.core.utils.Resource
import com.example.trendingtimesjetpack.domain.use_cases.SignUpUseCase
import com.example.trendingtimesjetpack.presentation.auth.emailEmptyErrorState
import com.example.trendingtimesjetpack.presentation.auth.invalidEmailErrorState
import com.example.trendingtimesjetpack.presentation.auth.invalidPasswordErrorState
import com.example.trendingtimesjetpack.presentation.auth.passwordEmptyErrorState
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.state.SignUpState
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.state.SignUpUiEvent
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.state.emptyGenderErrorState
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.state.emptyNameErrorState
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.state.invalidNameErrorState
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.state.mismatchPasswordErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState = _signUpState.asStateFlow()

    fun onUiEvent(event: SignUpUiEvent) {
        when (event) {
            is SignUpUiEvent.NameChanged -> {
                _signUpState.update { newState ->
                    newState.copy(
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
            }

            is SignUpUiEvent.EmailChanged -> {
                _signUpState.update { newState ->
                    newState.copy(
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
            }

            is SignUpUiEvent.PasswordChanged -> {
                _signUpState.update { newState ->
                    newState.copy(
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

            }

            is SignUpUiEvent.ConfirmPasswordChanged -> {
                _signUpState.update { newState ->
                    newState.copy(
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
            }

            is SignUpUiEvent.GenderSelected -> {
                _signUpState.update { newState ->
                    newState.copy(
                        gender = event.newGenderValue,
                        errorState = signUpState.value.errorState.copy(
                            genderErrorState = if (signUpState.value.gender.isEmpty())
                                emptyGenderErrorState
                            else
                                ErrorState()
                        )
                    )
                }
            }

            is SignUpUiEvent.SelectDob -> {
                _signUpState.update { newState ->
                    newState.copy(
                        dob = event.newDobValue,
                        isDobDialogOpen = false
                    )
                }
            }

            SignUpUiEvent.SignUpClick -> {
                signUpApiCall()
            }

            SignUpUiEvent.DobClick -> {
                _signUpState.update { newState ->
                    newState.copy(
                        isDobDialogOpen = !signUpState.value.isDobDialogOpen
                    )
                }
            }


            is SignUpUiEvent.PickedImage -> {
                _signUpState.update { newState ->
                    newState.copy(
                        pickedPhoto = event.uri
                    )
                }
            }
        }
    }

    private fun signUpApiCall() {
        if (!validateInputs()) {
            return
        }
        viewModelScope.launch {
            signUpUseCase.invoke(
                signUpState.value.name,
                signUpState.value.email,
                signUpState.value.password,
                signUpState.value.gender,
                signUpState.value.dob
            ).collect {
                when (it) {
                    is Resource.Error -> {
                        _signUpState.update { newState ->
                            newState.copy(
                                signUpInProgress = false,
                                isSignUpError = true,
                                signUpErrorString = it.message
                            )
                        }
                    }

                    Resource.Loading -> {
                        _signUpState.update { newState ->
                            newState.copy(
                                signUpInProgress = true
                            )
                        }
                    }

                    is Resource.Success -> {
                        if (it.data.success) {
                            _signUpState.update { newState ->
                                newState.copy(
                                    isSignUpSuccessful = true,
                                )
                            }
                        } else {
                            _signUpState.update { newState ->
                                newState.copy(
                                    signUpInProgress = false,
                                    isSignUpError = true,
                                    signUpErrorString = it.data.message
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val errorState = signUpState.value.errorState
        return !(errorState.emailErrorState.hasError || errorState.nameErrorState.hasError || errorState.passwordErrorState.hasError || errorState.confirmPasswordErrorState.hasError || signUpState.value.gender.isEmpty())
    }

    fun resetError() {
        _signUpState.update { newState ->
            newState.copy(
                isSignUpError = false,
                signUpErrorString = ""
            )
        }
    }

    fun resetSuccess() {
        _signUpState.update { newState ->
            newState.copy(
                isSignUpError = false,
                signUpErrorString = "",
                isSignUpSuccessful = false
            )
        }
    }
}