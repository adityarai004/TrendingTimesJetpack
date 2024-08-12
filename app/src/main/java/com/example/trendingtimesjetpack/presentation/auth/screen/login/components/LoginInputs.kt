package com.example.trendingtimesjetpack.presentation.auth.screen.login.components


import LoginState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.trendingtimesjetpack.R
import com.example.trendingtimesjetpack.presentation.auth.components.AuthTextField
import com.example.trendingtimesjetpack.presentation.auth.components.PasswordTextField

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    onEmailChange: (String) -> Unit,
    loginState: LoginState
) {
    AuthTextField(
        modifier = Modifier.fillMaxWidth(),
        value = loginState.email,
        onValueChange = onEmailChange,
        label = stringResource(id = R.string.enter_your_email),
        keyboardOptions = KeyboardOptions
            (
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        errorText = stringResource(id = loginState.errorState.emailOrMobileErrorState.errorMessageStringResource),
        isError = loginState.errorState.passwordErrorState.hasError
    )
}

@Composable
fun PasswordTextField(
    loginState: LoginState,

    onPasswordChanged: (String) -> Unit,
//    onSubmit: () -> Unit,
) {
    PasswordTextField(
        modifier = Modifier.fillMaxWidth(),
        value = loginState.password,
        onValueChange = onPasswordChanged,
        label = stringResource(id = R.string.enter_your_password),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        )
    )
}
//        CustomButton(onClick = onSubmit, text = "Login", modifier = Modifier.fillMaxWidth())
