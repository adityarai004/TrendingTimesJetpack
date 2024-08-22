package com.example.trendingtimesjetpack.data.data_sources.remote.news

import com.example.trendingtimesjetpack.data.dto.news.NewsDTO

interface NewsDataSource {
    suspend fun getNews(query: String, page: Int, perPage: Int): NewsDTO
}