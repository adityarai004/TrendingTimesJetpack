package com.example.trendingtimesjetpack.domain.use_cases

import android.util.Log
import androidx.paging.PagingData
import com.example.trendingtimesjetpack.data.data_sources.remote.news.NewsPagingFactory
import com.example.trendingtimesjetpack.data.data_sources.remote.news.NewsPagingSource
import com.example.trendingtimesjetpack.data.dto.news.Article
import com.example.trendingtimesjetpack.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsPagingFactory: NewsPagingFactory) {
    suspend operator fun invoke(query: String) : Flow<PagingData<Article>> {
        if(query.isNotEmpty()){
            Log.d("NEWS USE CASE","jdskfjklsjdf")
        }
        return newsPagingFactory.getNews(query)
    }
}