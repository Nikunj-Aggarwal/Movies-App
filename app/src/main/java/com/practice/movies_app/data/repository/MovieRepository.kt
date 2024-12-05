package com.practice.movies_app.data.repository

import com.practice.movies_app.data.manager.RetrofitClient
import com.practice.movies_app.data.model.MovieResponse

class MovieRepository {
    private val apiService = RetrofitClient.instance

    suspend fun getMovieData(apiKey: String): MovieResponse? {
        try {
            val response = apiService.getTrendingMovies(apiKey)
            return if (response.isSuccessful && response.body() != null) {
                response.body()
            } else {
                null
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            return null
        }
    }
}