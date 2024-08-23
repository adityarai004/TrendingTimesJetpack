package com.example.trendingtimesjetpack.core.constants

object NetworkConstants {
    const val BASE_URL = "https://trendingtimesbackend.onrender.com"

    const val LOGIN = "/auth/login"
    const val SIGN_UP = "/auth/register"

    const val TOP_HEADLINES = "top_headlines"
    const val BUSINESS = "business"
    const val SCIENCE = "/news/science"
    const val POLITICS = "politics"
    const val HEALTH = "health"
    const val EDUCATION = "education"
    const val TECHNOLOGY = "technology"
    const val ENTERTAINMENT = "entertainment"
    const val OPINION = "opinion"
    const val SPORTS = "sports"

    val NEWS_ENDPOINTS = arrayOf(
        "news/$TOP_HEADLINES",
        "news/$TECHNOLOGY",
        "news/$POLITICS",
        "news/$HEALTH",
        "news/$SCIENCE",
        "news/$ENTERTAINMENT",
        "news/$SPORTS",
        "news/$OPINION",
        "news/$BUSINESS",
        "news/$EDUCATION"
    )
}