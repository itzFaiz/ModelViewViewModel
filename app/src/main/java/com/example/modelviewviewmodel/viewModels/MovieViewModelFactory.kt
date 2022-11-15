package com.example.modelviewviewmodel.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modelviewviewmodel.repository.MovieRepository
import java.lang.IllegalArgumentException

class MovieViewModelFactory constructor(private val movieRepository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MovieViewModel::class.java)){
            MovieViewModel(this.movieRepository) as T
        }else{
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}