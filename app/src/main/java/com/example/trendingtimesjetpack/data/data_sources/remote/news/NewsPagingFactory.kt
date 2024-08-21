package com.example.trendingtimesjetpack.data.data_sources.remote.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import javax.inject.Inject

class NewsPagingFactory @Inject constructor(private val newsDataSource: NewsDataSource) {

    fun getNews(news: String) = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = { NewsPagingSource(newsDataSource = newsDataSource, query = news) }
    ).flow

}