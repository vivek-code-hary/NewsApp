package com.example.newsappdbretrofit.ApiServices

import com.example.newsappdbretrofit.Models.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// https://newsapi.org/v2/top-headlines?country=us&apiKey=2bd36b1d589747f88a3c276d79c4045f
const val API_KEY = "2bd36b1d589747f88a3c276d79c4045f"
interface NewsServices {

    @GET("v2/top-headlines?apiKey=2bd36b1d589747f88a3c276d79c4045f")
   suspend fun getHeadlines(@Query("country") country:String,@Query("page") page: Int): Response<News>
}

//https://newsapi.org/v2/top-headlines?apiKey=2bd36b1d589747f88a3c276d79c4045f