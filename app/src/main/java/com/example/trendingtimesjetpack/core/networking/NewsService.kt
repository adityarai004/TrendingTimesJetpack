package com.example.trendingtimesjetpack.core.networking

import android.util.Log
import com.example.trendingtimesjetpack.data.dto.news.NewsDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import javax.inject.Inject

class NewsService @Inject constructor(private val httpClient: HttpClient) {
    suspend fun getNews(newsEndpoint: String, page: Int) : NewsDTO {
        if(page > 1){
            Log.d("TAG","ISNDIE GET NEWS")
        }
        val response = httpClient.get(newsEndpoint){
            setBody(
                "page" to page
            )
        }.body<NewsDTO>()
        return response
    }
}