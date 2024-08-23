package com.example.trendingtimesjetpack.presentation.news.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.trendingtimesjetpack.core.constants.NetworkConstants
import com.example.trendingtimesjetpack.core.constants.NetworkConstants.NEWS_ENDPOINTS
import com.example.trendingtimesjetpack.data.dto.news.Article
import com.example.trendingtimesjetpack.domain.use_cases.GetNewsUseCase
import com.example.trendingtimesjetpack.presentation.news.screen.state.NewsEvent
import com.example.trendingtimesjetpack.presentation.news.screen.state.NewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {


    private val _newsUiState = MutableStateFlow(NewsUiState())
    val newsUiState = _newsUiState.asStateFlow()

    val newsList = MutableStateFlow<PagingData<Article>>(PagingData.empty())

    init {
        doApiCall(0)
    }

    fun onUiEvent(newsEvent: NewsEvent) {
        when (newsEvent) {
            is NewsEvent.ChangePage -> _newsUiState.update { newState ->
                if(newsUiState.value.newsMap.containsKey(newsEvent.page)){
                    doApiCall(newsEvent.page)
                }
                newState.copy(
                    selectedIndex = newsEvent.page
                )
            }
        }
    }

    fun doApiCall(index : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsUseCase.invoke(query = NEWS_ENDPOINTS[index]).collect {
                newsList.value = it
//                _newsUiState.update { newState ->
//                    newState.copy(
//                        newsMap = newState.newsMap.toMutableMap().apply {
//                            put(index,it)
//                        }
//                    )
//                }
            }
        }
    }
}