package com.example.trendingtimesjetpack.presentation.auth.screen.login

import LoginErrorState
import LoginState
import LoginUiEvent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendingtimesjetpack.core.ui.ErrorState
import emailOrMobileEmptyErrorState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import passwordEmptyErrorState


//@HiltViewModel
class LoginViewModel : ViewModel() {
    var loginState = mutableStateOf(LoginState())
        private set

    fun onUiEvent(loginUiEvent: LoginUiEvent) {
        when (loginUiEvent) {
            is LoginUiEvent.EmailChanged -> {
                loginState.value = loginState.value.copy(
                    email = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        emailOrMobileErrorState = if (loginUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            emailOrMobileEmptyErrorState
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

            is LoginUiEvent.Submit -> {
                val email = loginState.value.email
                val password = loginState.value.password
                val inputsValidated = validateInput(email, password)
                loginState.value = loginState.value.copy(
                    loginInProgress = true,
                    isLoginError = true,
                    loginErrorString = "Something Went Wrong"
                )

                if (inputsValidated) {
                    viewModelScope.launch(Dispatchers.IO) {
//                        loginUseCase.invoke(AuthDTO(email,password)).collect{it ->
//                            when(it){
//                                is Resource.Failure -> {
//                                    loginState.value = loginState.value.copy(loginInProgress = false, isLoginError = true, loginErrorString = it.error ?: "Something Went Wrong")
//
//                                }
//                                is Resource.Loading -> {
//                                    loginState.value = loginState.value.copy(loginInProgress = true)
//
//                                }
//                                is Resource.Success -> {
//                                    loginState.value = loginState.value.copy(loginInProgress = false)
//
//                                }
//                            }
//                        }
                    }
                }
            }

            else -> {}
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        return when {
            email.isEmpty() -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        emailOrMobileErrorState = emailOrMobileEmptyErrorState
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

    init {

    }
}