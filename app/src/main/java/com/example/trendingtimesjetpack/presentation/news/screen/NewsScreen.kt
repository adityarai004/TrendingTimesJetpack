package com.example.trendingtimesjetpack.presentation.news.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.trendingtimesjetpack.R
import com.example.trendingtimesjetpack.data.dto.news.Article
import com.example.trendingtimesjetpack.presentation.news.components.NewsItem
import com.example.trendingtimesjetpack.presentation.news.components.NewsSearchField
import com.example.trendingtimesjetpack.presentation.news.components.NewsTabView
import com.example.trendingtimesjetpack.presentation.news.components.NewsTopAppBar
import com.example.trendingtimesjetpack.presentation.news.screen.state.NewsEvent
import com.example.trendingtimesjetpack.presentation.news.screen.state.NewsUiState
import kotlinx.coroutines.launch


@Composable
fun NewsRoute(
    onNavigateToBookmarks: () -> Unit,
    onNavigateToSettings: () -> Unit,
    newsViewModel: NewsViewModel = hiltViewModel()
) {
    val newsState by newsViewModel.newsUiState.collectAsStateWithLifecycle()
    val newsLists = arrayListOf(
        newsState.newsListsState.topHeadlines.collectAsLazyPagingItems(),
        newsState.newsListsState.technology.collectAsLazyPagingItems(),
        newsState.newsListsState.politics.collectAsLazyPagingItems(),
        newsState.newsListsState.health.collectAsLazyPagingItems(),
        newsState.newsListsState.science.collectAsLazyPagingItems(),
        newsState.newsListsState.entertainment.collectAsLazyPagingItems(),
        newsState.newsListsState.sports.collectAsLazyPagingItems(),
        newsState.newsListsState.opinions.collectAsLazyPagingItems(),
        newsState.newsListsState.business.collectAsLazyPagingItems(),
        newsState.newsListsState.education.collectAsLazyPagingItems(),
    )
    NewsScreen(onNavigateToBookmarks, onNavigateToSettings, newsLists, newsState,
        onPageChange = {
            newsViewModel.onUiEvent(NewsEvent.ChangePage(it))
        })
}

@Composable
fun NewsScreen(
    onNavigateToBookmarks: () -> Unit,
    onNavigateToSettings: () -> Unit,
    categoryNews: ArrayList<LazyPagingItems<Article>>,
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
            NewsTopAppBar(
                onNavigateToBookmarks = onNavigateToBookmarks,
                onNavigateToSettings = onNavigateToSettings
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NewsSearchField(value = "", onValueChange = {})
            NewsTabView(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.purple_80))
                    .height(40.dp)
                    .align(Alignment.End),
                categoryList = newsUiState.categoryList,
                onTabClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(it)
                    }
                },
                selectedIndex = newsUiState.selectedIndex
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
            ) { page ->
                if (categoryNews[page].itemCount == 0) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn(verticalArrangement = Arrangement.Top) {
                        items(categoryNews[newsUiState.selectedIndex].itemCount) {
                            NewsItem(article = categoryNews[page][it])
                        }
                    }
                }

//                LazyColumn(verticalArrangement = Arrangement.Top) {
//                    newsListOld?.itemCount?.let { it1 ->
//                        items(it1) {
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(40.dp)
//                            ) {
//                                newsListOld[it]?.let { it1 -> it1.title?.let { it2 -> Text(text = it2) } }
//                            }
//                        }
//                    }
//                }
            }


            categoryNews.apply {
                when {
//                    loadState.refresh is LoadState.Loading -> {
//                        item { LoadingView(modifier = Modifier.fillMaxSize()) }
//                    }
//
//                    loadState.append is LoadState.Loading -> {
//                        item { LoadingItem() }
//                    }
//
//                    loadState.refresh is LoadState.Error -> {
//                        item {
//                            ErrorItem(
//                                message = "Something went wrong",
//                                modifier = Modifier.fillMaxSize(),
//                                onClickRetry = { retry() }
//                            )
//                        }
//                    }
//
//                    loadState.append is LoadState.Error -> {
//                        item {
//                            ErrorItem(
//                                message = "Something went wrong",
//                                onClickRetry = { retry() }
//                            )
//                        }
//                    }
//
//                    else -> {
//
//                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NewsScreenPreview(modifier: Modifier = Modifier) {
//    NewsScreen({}, {},
//        flowOf(
//            PagingData.from(
//                listOf(
//                    Article(
//                        title = "Understanding Kotlin Coroutines",
//                        author = "Jane Doe",
//                        content = "Kotlin coroutines are a powerful feature that allows for asynchronous programming..."
//                    ),
//                    Article(
//                        title = "Getting Started with Jetpack Compose",
//                        author = "John Smith",
//                        content = "Jetpack Compose is a modern toolkit for building native Android UI..."
//                    ),
//                    Article(
//                        title = "Introduction to Clean Architecture",
//                        author = "Emily Johnson",
//                        content = "Clean Architecture is a design pattern that helps in maintaining the separation of concerns..."
//                    ),
//                    Article(
//                        title = "Exploring Ktor for Android Development",
//                        author = "Michael Brown",
//                        content = "Ktor is a framework for building asynchronous servers and clients in connected systems..."
//                    ),
//                    Article(
//                        title = "Advanced Tips for Using Room Database",
//                        author = "Sarah Lee",
//                        content = "Room is a database abstraction layer that allows for seamless integration with SQLite..."
//                    )
//                )
//            )
//        ).collectAsLazyPagingItems(),
//        NewsUiState(),
//        onPageChange = {}
//    )
}