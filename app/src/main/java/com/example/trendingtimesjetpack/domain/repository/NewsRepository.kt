package com.example.trendingtimesjetpack.domain.repository

import androidx.paging.PagingData
import com.example.trendingtimesjetpack.data.dto.news.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getPagedNews(query: String): Flow<PagingData<Article>>
}