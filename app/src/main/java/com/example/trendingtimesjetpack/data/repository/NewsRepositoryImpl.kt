package com.example.trendingtimesjetpack.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.trendingtimesjetpack.core.networking.NewsService
import com.example.trendingtimesjetpack.data.data_sources.remote.news.NewsDataSourceImpl
import com.example.trendingtimesjetpack.data.dto.news.Article
import com.example.trendingtimesjetpack.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//class NewsRepositoryImpl @Inject constructor(private val newsService: NewsService){
//    suspend fun getPagedNews(query: String): Flow<PagingData<Article>> =


//}