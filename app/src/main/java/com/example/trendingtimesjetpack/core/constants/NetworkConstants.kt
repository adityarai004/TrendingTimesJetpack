package com.example.trendingtimesjetpack.core.constants

object NetworkConstants {
    const val BASE_URL = "https://trendingtimesbackend.onrender.com"

    const val LOGIN = "/auth/login"
    const val SIGN_UP = "/auth/register"

    const val TOP_HEADLINES = "news/top_headlines"
    const val BUSINESS = "news/business"
    const val SCIENCE = "news/science"
    const val POLITICS = "news/politics"
    const val HEALTH = "news/health"
    const val EDUCATION = "news/education"
    const val TECHNOLOGY = "news/technology"
    const val ENTERTAINMENT = "news/entertainment"
    const val OPINION = "news/opinion"
    const val SPORTS = "news/sports"

    val NEWS_ENDPOINTS = arrayOf(
        TOP_HEADLINES,
        TECHNOLOGY,
        POLITICS,
        HEALTH,
        SCIENCE,
        ENTERTAINMENT,
        SPORTS,
        OPINION,
        BUSINESS,
        EDUCATION,
    )
}