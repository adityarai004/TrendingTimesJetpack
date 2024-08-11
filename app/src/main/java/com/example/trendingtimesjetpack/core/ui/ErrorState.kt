package com.example.trendingtimesjetpack.core.ui

import androidx.annotation.StringRes
import com.example.trendingtimesjetpack.R

data class ErrorState(
    val hasError: Boolean = false,
    @StringRes val errorMessageStringResource: Int = R.string.empty_string
)