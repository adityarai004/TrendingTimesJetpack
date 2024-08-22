package com.example.trendingtimesjetpack.presentation.news.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.trendingtimesjetpack.R
import com.example.trendingtimesjetpack.data.dto.news.Article
import com.example.trendingtimesjetpack.presentation.auth.components.MediumTitleText
import com.example.trendingtimesjetpack.presentation.news.components.NewsSearchField
import com.example.trendingtimesjetpack.presentation.news.screen.state.NewsEvent
import com.example.trendingtimesjetpack.presentation.news.screen.state.NewsUiState
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch


@Composable
fun NewsRoute(
    onNavigateToBookmarks: () -> Unit,
    onNavigateToSettings: () -> Unit,
    newsViewModel: NewsViewModel = hiltViewModel()
) {
    val newsList = newsViewModel.newsList.collectAsLazyPagingItems()
    val newsState by newsViewModel.newsUiState.collectAsStateWithLifecycle()
    NewsScreen(onNavigateToBookmarks, onNavigateToSettings, newsList, newsState,
        onPageChange = {
            newsViewModel.onUiEvent(NewsEvent.ChangePage(it))
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    onNavigateToBookmarks: () -> Unit,
    onNavigateToSettings: () -> Unit,
    newsList: LazyPagingItems<Article>,
    newsUiState: NewsUiState,
    onPageChange: (Int) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0) { 10 }
    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress && pagerState.currentPage != newsUiState.selectedIndex) {
            Log.d("TAG", "Scrolled to ${pagerState.currentPage}")
            onPageChange(pagerState.currentPage)
        }
    }

    Scaffold(
        topBar = {
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
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NewsSearchField(value = "", onValueChange = {})
            LazyRow(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.purple_80))
                    .height(40.dp)
                    .align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(newsUiState.categoryList) { index ->
                    Box {
                        Text(
                            text = index,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .clickable(interactionSource = null, indication = null) {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(
                                            newsUiState.categoryList.indexOf(
                                                index
                                            )
                                        )
                                    }
                                },
                            fontWeight = FontWeight.Bold,
                            color = if (newsUiState.categoryList.indexOf(index) == newsUiState.selectedIndex) colorResource(
                                id = R.color.Lavender
                            ) else Color.Black,
                        )
                    }
                }
            }


            HorizontalPager(
                state = pagerState, modifier = Modifier.fillMaxWidth(),
            ) {
                LazyColumn(verticalArrangement = Arrangement.Top) {
                    items(newsList.itemCount) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                        ) {
                            newsList[it]?.let { it1 -> it1.title?.let { it2 -> Text(text = it2) } }
                        }
                    }
                }
            }


            newsList.apply {
                when {
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

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NewsScreenPreview(modifier: Modifier = Modifier) {
    NewsScreen({}, {}, flowOf(
        PagingData.from(
            listOf(
                Article(
                    title = "Understanding Kotlin Coroutines",
                    author = "Jane Doe",
                    content = "Kotlin coroutines are a powerful feature that allows for asynchronous programming..."
                ),
                Article(
                    title = "Getting Started with Jetpack Compose",
                    author = "John Smith",
                    content = "Jetpack Compose is a modern toolkit for building native Android UI..."
                ),
                Article(
                    title = "Introduction to Clean Architecture",
                    author = "Emily Johnson",
                    content = "Clean Architecture is a design pattern that helps in maintaining the separation of concerns..."
                ),
                Article(
                    title = "Exploring Ktor for Android Development",
                    author = "Michael Brown",
                    content = "Ktor is a framework for building asynchronous servers and clients in connected systems..."
                ),
                Article(
                    title = "Advanced Tips for Using Room Database",
                    author = "Sarah Lee",
                    content = "Room is a database abstraction layer that allows for seamless integration with SQLite..."
                )
            )
        )
    ).collectAsLazyPagingItems(),
        NewsUiState(),
        onPageChange = {}
    )
}