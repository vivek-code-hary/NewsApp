package com.example.newsappdbretrofit.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappdbretrofit.Models.Article
import com.example.newsappdbretrofit.Models.News
import com.example.newsappdbretrofit.Repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _articleLiveData = MutableLiveData<List<Article>>()
    val articleLiveData: LiveData<List<Article>>
        get() = _articleLiveData

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val news = repository.getNewsArticles("us", 1)
            _articleLiveData.postValue(news)
        }
    }
}