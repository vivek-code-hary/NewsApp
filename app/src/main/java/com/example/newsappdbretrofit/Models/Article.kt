package com.example.newsappdbretrofit.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Article(
    val author: String="",
    val content: String="",
    val description: String="",
    val publishedAt: String="",
    val source: Source=Source(),
    val title: String="",
    val url: String="",
    val urlToImage: String=""
)