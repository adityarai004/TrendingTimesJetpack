package com.example.trendingtimesjetpack.data.dto.news

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsDTO(
    @SerialName("articles")
    val articles: List<Article>,
    @SerialName("currentPage")
    val currentPage: Int,
    @SerialName("totalPages")
    val totalPages: Int,
    @SerialName("totalResults")
    val totalResults: Int
)