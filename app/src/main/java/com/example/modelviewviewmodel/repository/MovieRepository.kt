package com.example.modelviewviewmodel.repository

import com.example.modelviewviewmodel.api.RetrofitService

class MovieRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllMovies() = retrofitService.getAllMovies()

}