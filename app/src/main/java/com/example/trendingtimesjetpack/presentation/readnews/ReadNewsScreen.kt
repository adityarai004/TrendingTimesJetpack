package com.example.trendingtimesjetpack.presentation.readnews

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.trendingtimesjetpack.presentation.news.components.NewsTopAppBar

@Composable
fun ReadNewsRoute(newsUrl: String, backPress: () -> Unit) {
    Scaffold(topBar = {
        NewsTopAppBar(title = "Read News", navigationIcon = {
            IconButton(onClick = backPress) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        })
    }) { contentPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)) {

            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        loadUrl(newsUrl)
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    }
                },
            )
        }

    }

}