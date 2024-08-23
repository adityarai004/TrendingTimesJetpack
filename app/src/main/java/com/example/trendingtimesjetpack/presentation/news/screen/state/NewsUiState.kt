package com.example.trendingtimesjetpack.presentation.news.screen.state

import androidx.paging.PagingData
import com.example.trendingtimesjetpack.data.dto.news.Article
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

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
    val newsMap: Map<Int, List<Article>> = emptyMap()
)