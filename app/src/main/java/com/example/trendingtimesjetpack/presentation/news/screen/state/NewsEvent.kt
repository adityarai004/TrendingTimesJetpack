package com.example.trendingtimesjetpack.presentation.news.screen.state

sealed class NewsEvent {
    data class ChangePage(val page: Int): NewsEvent()
}