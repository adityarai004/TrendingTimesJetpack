package com.example.trendingtimesjetpack.data.dto.news

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    @SerialName("author")
    val author: String,
    @SerialName("content")
    val content: String,
    @SerialName("description")
    val description: String,
    @SerialName("_id")
    val id: String,
    @SerialName("likes")
    val likes: Int,
    @SerialName("publishedAt")
    val publishedAt: String,
    @SerialName("source")
    val source: Source,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String,
    @SerialName("urlToImage")
    val urlToImage: String
)