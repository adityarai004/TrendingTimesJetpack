package com.example.trendingtimesjetpack.data.dto.news

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Source(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String
)