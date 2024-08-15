package com.example.trendingtimesjetpack

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendingtimesjetpack.core.constants.LocalPrefsConstants
import com.example.trendingtimesjetpack.domain.use_cases.GetBooleanUseCase
import com.example.trendingtimesjetpack.domain.use_cases.GetUserAuthKeyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getBooleanUseCase: GetBooleanUseCase,private val getUserAuthKeyUseCase: GetUserAuthKeyUseCase) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoggedIn.value = getBooleanUseCase.invoke(LocalPrefsConstants.USER_IS_LOGGED_IN)
            _isLoading.value = false
        }
    }
}