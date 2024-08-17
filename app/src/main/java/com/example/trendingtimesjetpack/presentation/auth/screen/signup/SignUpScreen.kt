package com.example.trendingtimesjetpack.presentation.auth.screen.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trendingtimesjetpack.R
import com.example.trendingtimesjetpack.core.ui.ErrorState
import com.example.trendingtimesjetpack.presentation.auth.components.AuthTextField
import com.example.trendingtimesjetpack.presentation.auth.components.LargeTitleText
import com.example.trendingtimesjetpack.presentation.auth.components.MediumTitleText
import com.example.trendingtimesjetpack.presentation.auth.components.PasswordTextField
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.state.SignUpUiEvent

@Composable
fun SignUpScreen(
    onClickAlreadyHaveAccount: () -> Unit,
    signUpViewModel: SignUpViewModel = viewModel()
) {
    val signUpState by remember {
        signUpViewModel.signUpState
    }
    MainSignUpScreen(
        nameValue = signUpState.name,
        emailValue = signUpState.email,
        passwordValue = signUpState.password,
        confirmPasswordValue = signUpState.confirmPassword,
        onNameChange = { inputString ->
            signUpViewModel.onUiEvent(SignUpUiEvent.NameChanged(inputString))
        },
        onEmailChange = { inputString ->
            signUpViewModel.onUiEvent(SignUpUiEvent.EmailChanged(inputString))
        },
        onPasswordChange = { inputString ->
            signUpViewModel.onUiEvent(SignUpUiEvent.PasswordChanged(inputString))
        },
        confirmPasswordChange = { inputString ->
            signUpViewModel.onUiEvent(SignUpUiEvent.ConfirmPasswordChanged(inputString))
        },
        nameErrorState = signUpState.errorState.nameErrorState,
        emailErrorState = signUpState.errorState.emailErrorState,
        passwordErrorState = signUpState.errorState.passwordErrorState,
        confirmPasswordErrorState = signUpState.errorState.confirmPasswordErrorState
    )
}

@Composable
fun MainSignUpScreen(
    nameValue: String = "",
    onNameChange: (String) -> Unit,
    nameErrorState: ErrorState = ErrorState(),
    emailErrorState: ErrorState = ErrorState(),
    passwordErrorState: ErrorState = ErrorState(),
    confirmPasswordErrorState: ErrorState = ErrorState(),
    emailValue: String = "",
    onEmailChange: (String) -> Unit,
    passwordValue: String = "",
    onPasswordChange: (String) -> Unit,
    confirmPasswordValue: String = "",
    confirmPasswordChange: (String) -> Unit,
) {
    val scrollState = rememberScrollState()
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.signup),
                contentDescription = "Sign Up Screen",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f),
                contentScale = ContentScale.FillWidth,
            )
            ElevatedCard(
                shape = RoundedCornerShape(26.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors()
                    .copy(containerColor = colorResource(id = R.color.Lavender))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    LargeTitleText(text = stringResource(id = R.string.welcome))
                    MediumTitleText(text = stringResource(id = R.string.please_enter_your_information))
                    AuthTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = emailValue,
                        onValueChange = onEmailChange,
                        label = stringResource(id = R.string.enter_your_email),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        isError = emailErrorState.hasError,
                        errorText = stringResource(id = emailErrorState.errorMessageStringResource)
                    )
                    AuthTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = nameValue,
                        onValueChange = onNameChange,
                        label = stringResource(id = R.string.enter_your_name),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        isError = nameErrorState.hasError,
                        errorText = stringResource(nameErrorState.errorMessageStringResource)
                    )
                    PasswordTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = passwordValue,
                        onValueChange = onPasswordChange,
                        label = stringResource(id = R.string.enter_your_password),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        isError = passwordErrorState.hasError,
                        errorText = stringResource(passwordErrorState.errorMessageStringResource)
                    )
                    PasswordTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = confirmPasswordValue,
                        onValueChange = confirmPasswordChange,
                        label = stringResource(id = R.string.confirm_your_password),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        isError = confirmPasswordErrorState.hasError,
                        errorText = stringResource(id = confirmPasswordErrorState.errorMessageStringResource)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    MainSignUpScreen(
        onNameChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        confirmPasswordChange = {})
}