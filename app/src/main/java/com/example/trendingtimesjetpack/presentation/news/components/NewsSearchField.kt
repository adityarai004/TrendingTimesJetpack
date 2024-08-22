package com.example.trendingtimesjetpack.presentation.news.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction

@Composable
fun NewsSearchField(modifier: Modifier = Modifier, value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Search News") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search"
            )
        },
        colors = TextFieldDefaults.colors().copy(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Go,
            showKeyboardOnFocus = true
        ),
        maxLines = 1,
    )
}