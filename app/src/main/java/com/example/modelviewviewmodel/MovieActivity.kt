package com.example.modelviewviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.modelviewviewmodel.adapter.MovieAdapter
import com.example.modelviewviewmodel.api.RetrofitService
import com.example.modelviewviewmodel.databinding.ActivityMovieBinding
import com.example.modelviewviewmodel.repository.MovieRepository
import com.example.modelviewviewmodel.viewModels.MovieViewModel
import com.example.modelviewviewmodel.viewModels.MovieViewModelFactory

class MovieActivity : AppCompatActivity() {

    private val TAG = "MovieActivity"
    private lateinit var binding: ActivityMovieBinding
    lateinit var viewModel: MovieViewModel
    val adapter = MovieAdapter()
    private val retrofitService = RetrofitService.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this,MovieViewModelFactory(MovieRepository(retrofitService))).get(MovieViewModel::class.java)
        binding.recyclerview.adapter = adapter
        viewModel.movieList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setMovieList(it)
        })
        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.getAllMovies()
    }
}