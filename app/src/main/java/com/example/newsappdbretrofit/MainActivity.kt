package com.example.newsappdbretrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappdbretrofit.Adapter.NewsAdapter
import com.example.newsappdbretrofit.Database.NewsDatabase
import com.example.newsappdbretrofit.Repository.NewsRepository
import com.example.newsappdbretrofit.ViewModel.NewsViewModel
import com.example.newsappdbretrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articleService = RetrofitInstance.getApiService(this)
        val repository = NewsRepository(this, articleService, NewsDatabase.getDatabase(applicationContext))
        viewModel =
            ViewModelProvider(this, NewsViewModelFactory(repository))[NewsViewModel::class.java]


        newsAdapter = NewsAdapter()
        binding.newsList.layoutManager = LinearLayoutManager(this)
        binding.newsList.adapter = newsAdapter


        viewModel.articleLiveData.observe(this) { articles ->
            Log.d("Vivekanand", "News articles: $articles")
            newsAdapter.submitList(articles)
        }

    }
}