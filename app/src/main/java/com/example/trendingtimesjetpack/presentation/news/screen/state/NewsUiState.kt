package com.example.trendingtimesjetpack.presentation.news.screen.state

import androidx.paging.PagingData
import com.example.trendingtimesjetpack.data.dto.news.Article
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

data class NewsUiState(
    val loading: Boolean = false,
    val categoryList: List<String> = listOf(
        "Top-Headlines",
        "Technology",
        "Politics",
        "Health",
        "Science",
        "Entertainment",
        "Sports",
        "Opinion",
        "Business",
        "Education"
    ),
    val selectedIndex: Int = 0,
    var newsListsState: NewsListState = NewsListState(),
    val newsMap: Map<Int, PagingData<Article>> = emptyMap(),
    val isLoading: List<Int> = emptyList()
)

data class NewsListState(
    val topHeadlines : MutableStateFlow<PagingData<Article>> = MutableStateFlow(PagingData.empty()),
    val technology : MutableStateFlow<PagingData<Article>> = MutableStateFlow(PagingData.empty()),
    val politics : MutableStateFlow<PagingData<Article>> = MutableStateFlow(PagingData.empty()),
    val health : MutableStateFlow<PagingData<Article>> = MutableStateFlow(PagingData.empty()),
    val science : MutableStateFlow<PagingData<Article>> = MutableStateFlow(PagingData.empty()),
    val entertainment : MutableStateFlow<PagingData<Article>> = MutableStateFlow(PagingData.empty()),
    val sports: MutableStateFlow<PagingData<Article>> = MutableStateFlow(PagingData.empty()),
    val opinions : MutableStateFlow<PagingData<Article>> = MutableStateFlow(PagingData.empty()),
    val business : MutableStateFlow<PagingData<Article>> = MutableStateFlow(PagingData.empty()),
    val education : MutableStateFlow<PagingData<Article>> = MutableStateFlow(PagingData.empty())
)