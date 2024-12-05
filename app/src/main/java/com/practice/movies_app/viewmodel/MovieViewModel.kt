package com.practice.movies_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.movies_app.data.model.Movie
import com.practice.movies_app.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {
    private val repository = MovieRepository()
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies
    private var allMovies = listOf<Movie>()
    private val _filteredMovies = MutableStateFlow<List<Movie>>(emptyList())
    val filteredMovies: StateFlow<List<Movie>> get() = _filteredMovies

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchTrendingMovies(apiKey: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val response = repository.getMovieData(apiKey)
                response?.let {
                    _filteredMovies.value = it.results
                    _movies.value = it.results
                    allMovies = it.results
                } ?: run {
                    _error.value = "Failed to load movies"
                }
            } catch (e: Exception) {
                _error.value = "Failed to load movies: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    fun filterMovies(query: String) {
        if (query.isBlank()) {
            _filteredMovies.value = allMovies
        } else {
            _filteredMovies.value = allMovies.filter { movie ->
                movie.title.contains(query, ignoreCase = true)
            }
        }
    }
}