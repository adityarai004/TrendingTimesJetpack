package com.example.trendingtimesjetpack.data.data_sources.remote.news

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.trendingtimesjetpack.core.networking.NewsService
import com.example.trendingtimesjetpack.data.dto.news.Article
import javax.inject.Inject

class NewsPagingSource @Inject constructor(
    private val newsDataSource: NewsDataSource,
    private val query: String,
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        Log.d("TAG","REFRESH REFRESH REFREHS")
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try {
            // Start refresh at page 1 if undefined.

            if(true){
                Log.d("INSIDE","LOAD LOAD LOAD")
            }
            val nextPageNumber = params.key ?: 1
            val response = newsDataSource.getNews(query, nextPageNumber)
            return LoadResult.Page(
                data = response.articles,
                prevKey = if(nextPageNumber == 1) null else -1, // Only paging forward.
                nextKey = if (response.articles.isNotEmpty()) response.currentPage + 1 else null
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error for
            // expected errors (such as a network failure).
            return LoadResult.Error(
                e
            )
        }
    }
}

