package com.example.trendingtimesjetpack.presentation.auth.screen.login

import LoginUiEvent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trendingtimesjetpack.R
import com.example.trendingtimesjetpack.presentation.auth.components.CustomButton
import com.example.trendingtimesjetpack.presentation.auth.components.LargeTitleText
import com.example.trendingtimesjetpack.presentation.auth.components.MediumTitleText
import com.example.trendingtimesjetpack.presentation.auth.screen.login.components.AccountRow
import com.example.trendingtimesjetpack.presentation.auth.screen.login.components.EmailTextField
import com.example.trendingtimesjetpack.presentation.auth.screen.login.components.PasswordTextField
import com.example.trendingtimesjetpack.presentation.auth.screen.login.components.ThirdPartyLoginRow


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val loginState by remember {
        loginViewModel.loginState
    }
    if (loginState.isLoginError) {
        Toast.makeText(context, loginState.loginErrorString, Toast.LENGTH_LONG).show()
    }

    LaunchedEffect(key1 = Unit) {
        loginViewModel.callLogin()
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (loginState.isLoginSuccessful) {
            LaunchedEffect(key1 = true) {
//                navigator.replace(NewsScreen())
            }
        } else {
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
                        LargeTitleText(text = "Welcome")
                        MediumTitleText(text = "Please login with your credentials")
                        EmailTextField(onEmailChange = { inputString ->
                            loginViewModel.onUiEvent(
                                loginUiEvent = LoginUiEvent.EmailChanged(
                                    inputString
                                )
                            )
                        }, loginState = loginState)
                        PasswordTextField(
                            loginState = loginState,
                            onPasswordChanged = { inputString ->
                                loginViewModel.onUiEvent(LoginUiEvent.PasswordChanged(inputValue = inputString))
                            },
                        )
                        CustomButton(
                            onClick = {
                                loginViewModel.onUiEvent(LoginUiEvent.Submit)
                            },
                            text = stringResource(id = R.string.login),
                            modifier = Modifier.fillMaxWidth()
                        )
                        AccountRow(
                            modifier = Modifier
                                .fillMaxWidth(),
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
        if (loginState.loginInProgress) {
            CircularProgressIndicator(
                modifier = Modifier// Make it fill the entire screen
                    .alpha(0.5f) // Set a slight transparency overlay
                    .align(Alignment.Center) // Center the progress bar
            )
        }
    }
}

@Composable
@Preview
fun LoginScreenPrev(modifier: Modifier = Modifier) {
    LoginScreen()
}