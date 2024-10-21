package com.example.newsappdbretrofit.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    val author: String,
    val description: String,

    @PrimaryKey(autoGenerate = false)
    val title: String,

    val urlToImage: String
)