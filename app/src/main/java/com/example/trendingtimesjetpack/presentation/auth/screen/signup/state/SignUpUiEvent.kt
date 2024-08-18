package com.example.trendingtimesjetpack.presentation.auth.screen.signup.state

import android.net.Uri

sealed class SignUpUiEvent{
    data class NameChanged(val newValue: String) : SignUpUiEvent()
    data class EmailChanged(val newEmailValue: String) : SignUpUiEvent()
    data class PasswordChanged(val newPasswordValue: String): SignUpUiEvent()
    data class ConfirmPasswordChanged(val newConfirmPasswordChanged: String): SignUpUiEvent()
    data class SelectDob(val newDobValue: Long): SignUpUiEvent()
    data class GenderSelected(val newGenderValue: String): SignUpUiEvent()
    data object SignUpClick: SignUpUiEvent()
    data object DobClick: SignUpUiEvent()
    data class PickedImage(val uri: Uri): SignUpUiEvent()
}