package com.example.trendingtimesjetpack.presentation.auth.screen.login

import com.example.trendingtimesjetpack.presentation.auth.screen.login.state.LoginErrorState
import com.example.trendingtimesjetpack.presentation.auth.screen.login.state.LoginState
import LoginUiEvent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendingtimesjetpack.core.constants.RegexConstants
import com.example.trendingtimesjetpack.core.ui.ErrorState
import com.example.trendingtimesjetpack.core.utils.Resource
import com.example.trendingtimesjetpack.domain.use_cases.LoginUseCase
import com.example.trendingtimesjetpack.domain.use_cases.SetUserAuthTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.trendingtimesjetpack.presentation.auth.screen.login.state.emailEmptyErrorState
import com.example.trendingtimesjetpack.presentation.auth.screen.login.state.invalidEmailErrorState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.trendingtimesjetpack.presentation.auth.screen.login.state.passwordEmptyErrorState
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val setUserAuthTokenUseCase: SetUserAuthTokenUseCase,
) : ViewModel() {
    var loginState = mutableStateOf(LoginState())
        private set

    fun resetLoginError() {
        loginState.value = loginState.value.copy(isLoginError = false)
    }

    fun onUiEvent(loginUiEvent: LoginUiEvent) {
        when (loginUiEvent) {
            is LoginUiEvent.EmailChanged -> {
                loginState.value = loginState.value.copy(
                    email = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        emailOrMobileErrorState = if (loginUiEvent.inputValue.trim().isEmpty())
                            emailEmptyErrorState
                        else if (!loginUiEvent.inputValue.matches(Regex(RegexConstants.EMAIL_REGEX)))
                            invalidEmailErrorState
                        else
                            ErrorState()
                    )
                )
            }

            is LoginUiEvent.PasswordChanged -> {
                loginState.value = loginState.value.copy(
                    password = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        passwordErrorState = if (loginUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            passwordEmptyErrorState
                    )
                )
            }

            is LoginUiEvent.Submit -> handleSubmitButton()

            else -> {}
        }
    }

    private fun handleSubmitButton() {
        val email = loginState.value.email
        val password = loginState.value.password
        val inputsValidated = validateInput(email, password)

        if (inputsValidated) {
            callLoginApi(email, password)
        }
    }

    private fun callLoginApi(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.invoke(email, password).collect {
                when (it) {
                    is Resource.Error -> {
                        loginState.value = loginState.value.copy(
                            loginInProgress = false,
                            isLoginError = true,
                            loginErrorString = it.message
                        )
                    }

                    is Resource.Loading -> {
                        loginState.value = loginState.value.copy(loginInProgress = true)
                    }

                    is Resource.Success -> {
                        if (it.data.success) {
                            viewModelScope.launch(Dispatchers.IO) {
                                setUserAuthTokenUseCase.invoke(it.data.message)
                            }
                            loginState.value = loginState.value.copy(
                                loginInProgress = false,
                                isLoginError = false,
                                loginErrorString = "",
                                isLoginSuccessful = true
                            )
                        } else {
                            loginState.value = loginState.value.copy(
                                loginInProgress = false,
                                isLoginError = true,
                                loginErrorString = it.data.message,
                                isLoginSuccessful = false
                            )
                        }
                    }
                }
            }
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        return when {
            email.isEmpty() -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        emailOrMobileErrorState = emailEmptyErrorState
                    )
                )
                false
            }

            password.isEmpty() -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        passwordErrorState = passwordEmptyErrorState
                    )
                )
                false
            }

            // No errors
            else -> {
                // Set default error state
                loginState.value = loginState.value.copy(errorState = LoginErrorState())
                true
            }
        }
    }
}