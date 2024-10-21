package com.example.newsappdbretrofit

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.newsappdbretrofit.ApiServices.NewsServices
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {



    private const val BASE_URL = "https://newsapi.org/"

    fun getApiService(context: Context):NewsServices{
        val client = OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context))
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsServices::class.java)
    }
} 