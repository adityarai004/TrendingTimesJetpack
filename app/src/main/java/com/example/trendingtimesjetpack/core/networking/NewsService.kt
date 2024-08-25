package com.example.trendingtimesjetpack.core.networking

import com.example.trendingtimesjetpack.data.dto.news.NewsDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class NewsService @Inject constructor(private val httpClient: HttpClient) {
    suspend fun getNews(newsEndpoint: String, page: Int, perPage: Int) : NewsDTO {
        val response = httpClient.get(newsEndpoint){
            url{
                parameters.append("page", page.toString())
                parameters.append("limit", "20")
            }
        }.body<NewsDTO>()
        return response
    }
}