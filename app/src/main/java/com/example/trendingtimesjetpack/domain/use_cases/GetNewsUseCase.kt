package com.example.trendingtimesjetpack.domain.use_cases

import androidx.paging.PagingData
import com.example.trendingtimesjetpack.data.data_sources.remote.news.NewsPagingFactory
import com.example.trendingtimesjetpack.data.dto.news.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsPagingFactory: NewsPagingFactory) {
    operator fun invoke(query: String): Flow<PagingData<Article>> {
        return newsPagingFactory.getNews(query)
    }
}