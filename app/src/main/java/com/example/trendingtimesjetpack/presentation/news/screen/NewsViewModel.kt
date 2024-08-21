package com.example.trendingtimesjetpack.presentation.news.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.trendingtimesjetpack.core.constants.NetworkConstants
import com.example.trendingtimesjetpack.data.dto.news.Article
import com.example.trendingtimesjetpack.domain.use_cases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {
    val newsCategories = mutableListOf(
        "Top-Headlines",
        "Technology",
        "Politics",
        "Health",
        "Science",
        "Entertainment",
        "Sports"
    )

    var newsList: Flow<PagingData<Article>> = flow {}

    init {
        viewModelScope.launch(Dispatchers.IO) {
            newsList = getNewsUseCase.invoke(query = NetworkConstants.SCIENCE)
        }
    }

    fun doApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsUseCase.invoke(query = NetworkConstants.SCIENCE).collect{
                Log.d("TAG","Collecting ${it.toString()}")
            }
        }
    }
}