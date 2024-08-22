package com.example.trendingtimesjetpack.presentation.news.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.trendingtimesjetpack.core.constants.NetworkConstants
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {

    private var _newsList = Channel<PagingData<Article>>()
    val newsList = _newsList.receiveAsFlow()

    private val _newsUiState = MutableStateFlow(NewsUiState())
    val newsUiState = _newsUiState.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsUseCase.invoke(query = NetworkConstants.SCIENCE).collect{
                _newsList.send(it)
            }
        }
    }

    fun onUiEvent(newsEvent: NewsEvent){
        when(newsEvent){
            is NewsEvent.ChangePage -> _newsUiState.update { newState ->
                newState.copy(
                    selectedIndex = newsEvent.page
                )
            }
        }
    }

//    fun doApiCall() {
//        viewModelScope.launch(Dispatchers.IO) {
//            getNewsUseCase.invoke(query = NetworkConstants.SCIENCE).collect{
//                Log.d("TAG","Collecting ${it.toString()}")
//            }
//        }
//    }
}