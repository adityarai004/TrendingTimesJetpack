package com.example.trendingtimesjetpack.presentation.news.screen.state

sealed interface NewsUiState {
    data object Loading: NewsUiState
}