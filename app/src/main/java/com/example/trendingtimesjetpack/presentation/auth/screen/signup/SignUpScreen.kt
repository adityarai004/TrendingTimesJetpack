package com.example.trendingtimesjetpack.presentation.auth.screen.signup

import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
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
import java.time.Instant
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onClickAlreadyHaveAccount: () -> Unit,
    signUpViewModel: SignUpViewModel = viewModel()
) {
    val signUpState by remember {
        signUpViewModel.signUpState
    }

    if (signUpState.isDobDialogOpen) {
        val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                    val minimumAgeInMilliseconds =
                        15L * 365 * 24 * 60 * 60 * 1000 // 15 years in milliseconds
                    val minimumSelectableDate =
                        Instant.now().toEpochMilli() - minimumAgeInMilliseconds
                    return utcTimeMillis >= minimumSelectableDate
                } else {
                    utcTimeMillis - System.currentTimeMillis() > 15
                }
            }
        })
        val confirmEnabled = remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(
            onDismissRequest = {
                signUpViewModel.onUiEvent(SignUpUiEvent.DobClick)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        signUpViewModel.onUiEvent(SignUpUiEvent.DobClick)
                        Log.d(
                            "DOB SELECTION",
                            "Selected date timestamp: ${datePickerState.selectedDateMillis}"
                        )
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { signUpViewModel.onUiEvent(SignUpUiEvent.DobClick) }) { Text("Cancel") }
            }) {
            DatePicker(state = datePickerState)
        }
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
        confirmPasswordErrorState = signUpState.errorState.confirmPasswordErrorState,
        radioOptions = arrayListOf("Male", "Female", "Other"),
        selectedOption = signUpState.gender,
        radioSelectionChange = { newSelection ->
            signUpViewModel.onUiEvent(SignUpUiEvent.GenderSelected(newSelection))
        },
        onClickDob = {
            signUpViewModel.onUiEvent(SignUpUiEvent.DobClick)
        }
    )
}

@Composable
fun MainSignUpScreen(
    nameValue: String,
    onNameChange: (String) -> Unit,
    nameErrorState: ErrorState,
    emailErrorState: ErrorState,
    passwordErrorState: ErrorState,
    confirmPasswordErrorState: ErrorState,
    emailValue: String,
    onEmailChange: (String) -> Unit,
    passwordValue: String,
    onPasswordChange: (String) -> Unit,
    confirmPasswordValue: String,
    confirmPasswordChange: (String) -> Unit,
    radioOptions: ArrayList<String>,
    selectedOption: String,
    radioSelectionChange: (String) -> Unit,
    onClickDob: () -> Unit
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
                    MediumTitleText(text = "Gender", modifier = Modifier.padding(start = 12.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        Modifier
                            .selectableGroup()
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        radioOptions.forEach { genderType ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.selectable(
                                    selected = (genderType == selectedOption),
                                    onClick = { radioSelectionChange(genderType) },
                                    role = Role.RadioButton
                                )
                            ) {
                                RadioButton(
                                    selected = (genderType == selectedOption),
                                    onClick = null
                                )
                                MediumTitleText(text = genderType)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    MediumTitleText(
                        text = "Date Of Birth",
                        modifier = Modifier.padding(start = 12.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .clickable {
                                onClickDob()
                            },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MediumTitleText(text = "09/11/2001")
                        Icon(imageVector = Icons.Filled.DateRange, contentDescription = "DOB")
                    }
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
        confirmPasswordChange = {},
        radioOptions = arrayListOf("Male", "Female", "Other"),
        radioSelectionChange = {},
        passwordValue = "",
        selectedOption = "",
        emailValue = "",
        nameValue = "",
        confirmPasswordValue = "",
        nameErrorState = ErrorState(),
        emailErrorState = ErrorState(),
        passwordErrorState = ErrorState(),
        confirmPasswordErrorState = ErrorState(),
        onClickDob = {}
    )
}