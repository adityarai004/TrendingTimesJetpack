package com.example.trendingtimesjetpack.presentation.auth.screen.login

import LoginErrorState
import LoginState
import LoginUiEvent
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendingtimesjetpack.core.ui.ErrorState
import com.example.trendingtimesjetpack.core.utils.Resource
import com.example.trendingtimesjetpack.data.dto.auth.LoginResponseDTO
import com.example.trendingtimesjetpack.domain.use_cases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import emailOrMobileEmptyErrorState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import passwordEmptyErrorState
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    var loginState = mutableStateOf(LoginState())
        private set

    suspend fun callLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase
                .invoke("example2@gmail.com", "123456").collect {
                    when (it) {
                        is Resource.Error -> Log.i("masti", "Error hua ji ${it.message}")
                        Resource.Loading -> Log.i("masti", " Loading")
                        is Resource.Success -> Log.i("masti", " lkdjsflkasjd ${it.data}")
                    }
                }
        }
    }

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