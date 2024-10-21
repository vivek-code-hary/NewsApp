package com.example.newsappdbretrofit.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsappdbretrofit.Dao.ArticleDao
import com.example.newsappdbretrofit.Models.Article
import com.example.newsappdbretrofit.Models.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {

   abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null
        const val DATABASE_NAME = "article_db"

        fun getDatabase(context: Context) : NewsDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }


}