package com.example.trendingtimesjetpack.presentation.news.screen.state

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
    val selectedIndex: Int = 0
)