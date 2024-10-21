package com.example.newsappdbretrofit.Repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.newsappdbretrofit.ApiServices.NewsServices
import com.example.newsappdbretrofit.Database.NewsDatabase
import com.example.newsappdbretrofit.Models.Article
import com.example.newsappdbretrofit.Models.ArticleEntity


class NewsRepository(
    private val context: Context,
    private val articleService: NewsServices,
    private val newsDatabase: NewsDatabase
) {




    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    suspend fun getNewsArticles(country: String, page: Int): List<Article> {
        return if (isInternetAvailable(context)) {
            val response = articleService.getHeadlines(country, page)
            if(response.body() != null){
                Log.d("Response Is Successful", "getNewsArticles: ${response.body()}")
                val articles = response.body()!!.articles
                val list = articles.map{
                    ArticleEntity(
                        author = it.author ?: "Unknown",
                        title = it.title ?: "No Title",
                        description = it.description ?: "No Description",
                        urlToImage = it.urlToImage ?: ""
                    ) 
                }
                newsDatabase.articleDao().insertAll(list)
                articles ?: emptyList()
            }else {
                emptyList()
            }
        }else{
            val articles = newsDatabase.articleDao().getAllArticles()
           val list = articles.map{
                Article(
                    author = it.author,
                    title = it.title,
                    description = it.description,
                    urlToImage = it.urlToImage
                )
            }
            list ?: emptyList()
        }
    }

}