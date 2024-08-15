package com.example.trendingtimesjetpack.presentation.auth.screen.login

import LoginUiEvent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trendingtimesjetpack.R
import com.example.trendingtimesjetpack.presentation.auth.components.AuthTextField
import com.example.trendingtimesjetpack.presentation.auth.components.CustomButton
import com.example.trendingtimesjetpack.presentation.auth.components.LargeTitleText
import com.example.trendingtimesjetpack.presentation.auth.components.MediumTitleText
import com.example.trendingtimesjetpack.presentation.auth.components.PasswordTextField
import com.example.trendingtimesjetpack.presentation.auth.screen.login.components.AccountRow
import com.example.trendingtimesjetpack.presentation.auth.screen.login.components.ThirdPartyLoginRow
import com.example.trendingtimesjetpack.presentation.auth.screen.login.state.LoginState


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigateToNews: () -> Unit
) {
    val context = LocalContext.current
    val loginState by remember {
        loginViewModel.loginState
    }
    val state = rememberScrollState()
    LaunchedEffect(key1 = loginState.isLoginError) {
        if (loginState.isLoginError) {
            Toast.makeText(context, loginState.loginErrorString, Toast.LENGTH_LONG).show()
        }
        loginViewModel.resetLoginError()
    }
    LaunchedEffect(key1 = loginState.isLoginSuccessful) {
        if (loginState.isLoginSuccessful) {
            onNavigateToNews()
        }
    }

    Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.verticalScroll(state)) {
                MainScreen(loginViewModel = loginViewModel, loginState = loginState)
            }
            if (loginState.loginInProgress) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f))
                        .pointerInput(Unit) {}
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun MainScreen(loginViewModel: LoginViewModel, loginState: LoginState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "Login Background",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors()
                .copy(containerColor = colorResource(id = R.color.Lavender))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                LargeTitleText(text = stringResource(id = R.string.welcome))
                MediumTitleText(text = stringResource(id = R.string.please_login_with_credentials))
                AuthTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = loginState.email,
                    onValueChange = { inputString ->
                        loginViewModel.onUiEvent(
                            loginUiEvent = LoginUiEvent.EmailChanged(
                                inputString
                            )
                        )
                    },
                    label = stringResource(id = R.string.enter_your_email),
                    keyboardOptions = KeyboardOptions
                        (
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    ),
                    errorText = stringResource(id = loginState.errorState.emailOrMobileErrorState.errorMessageStringResource),
                    isError = loginState.errorState.emailOrMobileErrorState.hasError
                )
                PasswordTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = loginState.password,
                    onValueChange = { inputString ->
                        loginViewModel.onUiEvent(LoginUiEvent.PasswordChanged(inputValue = inputString))
                    },
                    label = stringResource(id = R.string.enter_your_password),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    errorText = stringResource(id = loginState.errorState.passwordErrorState.errorMessageStringResource),
                    isError = loginState.errorState.passwordErrorState.hasError
                )
                CustomButton(
                    onClick = {
                        loginViewModel.onUiEvent(LoginUiEvent.Submit)
                    },
                    text = stringResource(id = R.string.login),
                    modifier = Modifier.fillMaxWidth()
                )
                AccountRow(
                    modifier = Modifier.fillMaxWidth(),
                )
                Text(
                    stringResource(id = R.string.or_continue_with),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                ThirdPartyLoginRow(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
@Preview
fun LoginScreenPrev(modifier: Modifier = Modifier) {
    LoginScreen(onNavigateToNews = {})
}