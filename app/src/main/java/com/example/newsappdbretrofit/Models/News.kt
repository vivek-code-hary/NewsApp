package com.example.newsappdbretrofit.Models

import androidx.lifecycle.LiveData

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)