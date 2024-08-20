package com.example.trendingtimesjetpack.presentation.news.screen

import androidx.lifecycle.ViewModel

class NewsViewModel: ViewModel() {
    val newsCategories = mutableListOf("Top-Headlines", "Technology", "Politics", "Health", "Science", "Entertainment", "Sports")
}