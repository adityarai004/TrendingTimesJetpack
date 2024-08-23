package com.example.trendingtimesjetpack.presentation.news.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.trendingtimesjetpack.R
import com.example.trendingtimesjetpack.presentation.auth.components.MediumTitleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopAppBar(
    modifier: Modifier = Modifier,
    onNavigateToBookmarks: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors()
            .copy(containerColor = colorResource(id = R.color.Lavender)),
        title = {
            MediumTitleText(text = "Trending Times", color = Color.White)
        },
        actions = {
            IconButton(onClick = onNavigateToBookmarks) {
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = "Bookmarks Buttons"
                )
            }
            IconButton(onClick = { onNavigateToSettings() }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings Button"
                )
            }
        },
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = "",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
            )
        },
    )
}