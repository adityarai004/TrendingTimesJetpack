package com.example.trendingtimesjetpack.presentation.news.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.trendingtimesjetpack.R
import com.example.trendingtimesjetpack.presentation.auth.components.LargeTitleText

@Composable
fun NewsScreen(
    onNavigateToBookmarks: () -> Unit,
    onNavigateToSettings: () -> Unit,
    newsViewModel: NewsViewModel = hiltViewModel()
) {

    val newsList = newsViewModel.newsList.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(R.color.Lavender))
                    .padding(5.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo2),
                    contentDescription = "",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                )
                LargeTitleText(text = "Trending Times", color = Color.White)
                Row {
                    IconButton(onClick = onNavigateToBookmarks) {
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = "Bookmarks Buttons"
                        )
                    }
                    IconButton(onClick = { newsViewModel.doApiCall() }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings Button"
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TextField(
                value = "",
                onValueChange = {},
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

            LazyRow {
                items(newsViewModel.newsCategories){ index ->
                    Text(text = index)
                }
                item {
                }
            }

            newsList.apply {
                when{
                    loadState.refresh is LoadState.Loading -> {
//                        item { LoadingView(modifier = Modifier.fillMaxSize()) }
                    }

                    loadState.append is LoadState.Loading -> {
//                        item { LoadingItem() }
                    }

                    loadState.refresh is LoadState.Error -> {
//                        item {
//                            ErrorItem(
//                                message = "Something went wrong",
//                                modifier = Modifier.fillMaxSize(),
//                                onClickRetry = { retry() }
//                            )
//                        }
                    }

                    loadState.append is LoadState.Error -> {
//                        item {
//                            ErrorItem(
//                                message = "Something went wrong",
//                                onClickRetry = { retry() }
//                            )
//                        }
                    }

                    else -> {
                        LazyColumn {
                            items(newsList.itemCount){
                                Row (modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)){
                                    newsList[it]?.let { it1 -> Text(text = it1.title) }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NewsScreenPreview(modifier: Modifier = Modifier) {
    NewsScreen({},{})
}