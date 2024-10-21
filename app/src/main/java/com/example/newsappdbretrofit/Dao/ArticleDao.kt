package com.example.newsappdbretrofit.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsappdbretrofit.Models.Article
import com.example.newsappdbretrofit.Models.ArticleEntity


@Dao
interface ArticleDao {

    // Insert all articles into the Room database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles:List<ArticleEntity>)

    // Retrieve all articles from the Room database
    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<ArticleEntity>
}