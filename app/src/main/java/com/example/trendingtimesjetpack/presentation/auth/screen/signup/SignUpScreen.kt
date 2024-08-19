package com.example.trendingtimesjetpack.presentation.auth.screen.signup

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trendingtimesjetpack.R
import com.example.trendingtimesjetpack.presentation.auth.components.CustomButton
import com.example.trendingtimesjetpack.presentation.auth.components.LargeTitleText
import com.example.trendingtimesjetpack.presentation.auth.components.MediumTitleText
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.components.ImagePicker
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.components.SignUpTextFields
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.state.SignUpState
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.state.SignUpUiEvent
import java.time.Instant
import java.time.ZoneId

@Composable
fun SignUpRoute(
    onClickAlreadyHaveAccount: () -> Unit,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val signUpState by signUpViewModel.signUpState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val pickImageContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                signUpViewModel.onUiEvent(SignUpUiEvent.PickedImage(uri))
            }
        }
    )

    val requestPermissionContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                pickImageContract.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
            } else {
                Toast.makeText(context, "Please grant permissions from settings", Toast.LENGTH_LONG)
                    .show()
            }
        }
    )


    LaunchedEffect(key1 = signUpState.isSignUpError) {
        if (signUpState.isSignUpError) {
            Toast.makeText(context, signUpState.signUpErrorString, Toast.LENGTH_LONG).show()
        }
        signUpViewModel.resetError()
    }

    LaunchedEffect(key1 = signUpState.isSignUpSuccessful) {
        if (signUpState.isSignUpSuccessful) {
            Toast.makeText(
                context,
                "Sign Up Successful, Please Login With Your Credentials",
                Toast.LENGTH_LONG
            ).show()
            onClickAlreadyHaveAccount()
        }
        signUpViewModel.resetSuccess()
    }

    SignUpScreen(
        signUpState = signUpState,
        radioOptions = arrayListOf("Male", "Female", "Other"),
        radioSelectionChange = { newSelection ->
            signUpViewModel.onUiEvent(SignUpUiEvent.GenderSelected(newSelection))
        },
        onClickDob = {
            signUpViewModel.onUiEvent(SignUpUiEvent.DobClick)
        },
        onNameChange = { inputString ->
            signUpViewModel.onUiEvent(SignUpUiEvent.NameChanged(inputString))
        },
        onEmailChange = { inputString ->
            signUpViewModel.onUiEvent(SignUpUiEvent.EmailChanged(inputString))
        },
        onPasswordChange = { inputString ->
            signUpViewModel.onUiEvent(SignUpUiEvent.PasswordChanged(inputString))
        },
        onConfirmPasswordChange = { inputString ->
            signUpViewModel.onUiEvent(SignUpUiEvent.ConfirmPasswordChanged(inputString))
        },
        onDismissRequest = {
            signUpViewModel.onUiEvent(SignUpUiEvent.DobClick)
        },
        onConfirmClick = { selectedDob ->
            signUpViewModel.onUiEvent(SignUpUiEvent.SelectDob(selectedDob))
        },
        onUploadImageClick = {
            val isTiramisu = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
            val hasMediaImagePermission: Boolean =
                if (isTiramisu) ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_MEDIA_IMAGES
                ) == PackageManager.PERMISSION_GRANTED else ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            if ((isTiramisu && hasMediaImagePermission) || hasMediaImagePermission) {
                pickImageContract.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
            } else {
                requestPermissionContract.launch(if (isTiramisu) Manifest.permission.READ_MEDIA_IMAGES else Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        },
        onNavigateToLogin = onClickAlreadyHaveAccount,
        onSignUpClick = {
            signUpViewModel.onUiEvent(SignUpUiEvent.SignUpClick)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    radioOptions: ArrayList<String>,
    radioSelectionChange: (String) -> Unit,
    onClickDob: () -> Unit,
    signUpState: SignUpState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirmClick: (Long) -> Unit,
    onUploadImageClick: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onSignUpClick: () -> Unit
) {


    if (signUpState.isDobDialogOpen) {
        val datePickerState = rememberDatePickerState(
            yearRange = 1950..2005,
            initialSelectedDateMillis = 946684800000
        )
        val confirmEnabled = remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(
            onDismissRequest = onDismissRequest,
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmClick(
                            datePickerState.selectedDateMillis ?: 946684800000L
                        )
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) { Text("Cancel") }
            }) {
            DatePicker(state = datePickerState)
        }
    }
    val scrollState = rememberScrollState()
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors()
                        .copy(containerColor = colorResource(id = R.color.Lavender))
                ) {
                    LargeTitleText(text = "Create a New Account")
                    Column(modifier = Modifier.padding(12.dp)) {
                        ImagePicker(
                            modifier = Modifier.fillMaxWidth(),
                            filePath = signUpState.pickedPhoto, onClickUpload =
                            onUploadImageClick
                        )
                        LargeTitleText(text = stringResource(id = R.string.welcome))
                        MediumTitleText(text = stringResource(id = R.string.please_enter_your_information))
                        SignUpTextFields(
                            nameValue = signUpState.name,
                            emailValue = signUpState.email,
                            passwordValue = signUpState.password,
                            confirmPasswordValue = signUpState.confirmPassword,
                            onNameChange = onNameChange,
                            onEmailChange = onEmailChange,
                            onPasswordChange = onPasswordChange,
                            confirmPasswordChange = onConfirmPasswordChange,
                            nameErrorState = signUpState.errorState.nameErrorState,
                            emailErrorState = signUpState.errorState.emailErrorState,
                            passwordErrorState = signUpState.errorState.passwordErrorState,
                            confirmPasswordErrorState = signUpState.errorState.confirmPasswordErrorState,
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
                                        selected = (genderType == signUpState.gender),
                                        onClick = { radioSelectionChange(genderType) },
                                        role = Role.RadioButton,
                                        interactionSource = null, indication = null
                                    )
                                ) {
                                    RadioButton(
                                        selected = (genderType == signUpState.gender),
                                        onClick = null
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
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
                                .clickable(
                                    interactionSource = null, indication = null
                                ) {
                                    onClickDob()
                                },
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                MediumTitleText(
                                    text = Instant.ofEpochMilli(signUpState.dob)
                                        .atZone(ZoneId.of("UTC")).toLocalDate().toString()
                                )
                            }
                            Icon(imageVector = Icons.Filled.DateRange, contentDescription = "DOB")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        MediumTitleText(
                            text = "Already Have An Account?",
                            modifier = Modifier
                                .clickable(interactionSource = null, indication = null) {
                                    onNavigateToLogin()
                                }
                                .padding(start = 8.dp),
                            color = colorResource(id = R.color.black)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomButton(
                            onClick = onSignUpClick,
                            text = "Sign Up",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            if (signUpState.signUpInProgress) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {}
                    .background(Color.Black.copy(alpha = 0.4f))) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(
        onNameChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        onConfirmPasswordChange = {},
        signUpState = SignUpState(),
        radioOptions = arrayListOf("Male", "Female", "Other"),
        radioSelectionChange = {},
        onClickDob = {},
        onConfirmClick = {},
        onDismissRequest = {},
        onUploadImageClick = {},
        onNavigateToLogin = {},
        onSignUpClick = {}
    )
}