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
                val updatableMap = newState.newsMap.toMutableMap()
                val isLoading = newState.isLoading.toMutableList()
                if (!newsUiState.value.newsMap.containsKey(newsEvent.page)) {
                    updatableMap[newsEvent.page] = PagingData.empty()
                    isLoading.add(newsEvent.page)
                    doApiCall(newsEvent.page)
                }
                newState.copy(
                    selectedIndex = newsEvent.page,
                    newsMap = updatableMap,
                    isLoading = isLoading
                )
            }
        }
    }

    private fun doApiCall(index: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsUseCase.invoke(query = NEWS_ENDPOINTS[index]).collect { pagingData ->
                when (_newsUiState.value.selectedIndex) {
                    0 -> {
                        _newsUiState.value.newsListsState.topHeadlines.value = pagingData
                        _newsUiState.update { newState ->
                            val updatableMap = newState.newsMap.toMutableMap()
                            val isLoading = newState.isLoading.toMutableList()
                            isLoading.add(0)
                            newState.copy(
                                newsMap = updatableMap,
                                isLoading = isLoading
                            )
                        }
                    }

                    1 -> {
                        _newsUiState.value.newsListsState.technology.value = pagingData
                    }

                    2 -> {
                        _newsUiState.value.newsListsState.politics.value = pagingData
                    }

                    3 -> {
                        _newsUiState.value.newsListsState.health.value = pagingData
                    }

                    4 -> {
                        _newsUiState.value.newsListsState.science.value = pagingData
                    }

                    5 -> {
                        _newsUiState.value.newsListsState.entertainment.value = pagingData
                    }

                    6 -> {
                        _newsUiState.value.newsListsState.sports.value = pagingData
                    }

                    7 -> {
                        _newsUiState.value.newsListsState.opinions.value = pagingData
                    }

                    8 -> {
                        _newsUiState.value.newsListsState.business.value = pagingData
                    }

                    9 -> {
                        _newsUiState.value.newsListsState.education.value = pagingData
                    }
                }
            }
        }
    }
}