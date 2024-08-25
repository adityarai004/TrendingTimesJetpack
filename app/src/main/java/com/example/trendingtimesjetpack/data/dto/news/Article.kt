package com.example.trendingtimesjetpack.data.dto.news

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@Serializable
data class Article(
    @SerialName("author")
    val author: String? = null,
    @SerialName("content")
    val content: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("_id")
    val id: String? = null,
    @SerialName("likes")
    val likes: Int? = null,
    @SerialName("publishedAt")
    val publishedAt: String? = null,
    @SerialName("source")
    val source: Source? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("urlToImage")
    val urlToImage: String? = null
){
    val timeDifference: String = getTimeDifference(publishedAt ?: "2024-08-26T12:34:45Z")

    private fun getTimeDifference(timestamp: String) : String{
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")

        val date = sdf.parse(timestamp)
        val currentTime = System.currentTimeMillis()

        val diffMillis = currentTime - date!!.time
        val diffSeconds = diffMillis / 1000
        val diffMinutes = diffSeconds / 60
        val diffHours = diffMinutes / 60
        val diffDays = diffHours / 24

        return when {
            diffDays > 0 -> "$diffDays day${if (diffDays > 1) "s" else ""} ago"
            diffHours > 0 -> "$diffHours hour${if (diffHours > 1) "s" else ""} ago"
            diffMinutes > 0 -> "$diffMinutes minute${if (diffMinutes > 1) "s" else ""} ago"
            else -> "Just now"
        }

    }
}