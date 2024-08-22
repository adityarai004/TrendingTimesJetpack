package com.example.trendingtimesjetpack.data.data_sources.remote.news

import com.example.trendingtimesjetpack.core.networking.NewsService
import com.example.trendingtimesjetpack.data.dto.news.NewsDTO
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(private val newsService: NewsService) :
    NewsDataSource {
    override suspend fun getNews(query: String, page: Int, perPage: Int): NewsDTO {
        return newsService.getNews(query, page, perPage)
    }

}

