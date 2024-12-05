package com.practice.movies_app

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.practice.movies_app.ui.movieDetail.MovieDetailScreen
import com.practice.movies_app.ui.movieList.MovieListScreen
import com.practice.movies_app.viewmodel.MovieViewModel

@Composable
fun AppNavGraph(viewModel: MovieViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "movieList") {
        // Movie List showing Trending Movies from API using 500w images
        composable("movieList") {
            MovieListScreen(viewModel) { selectedMovie ->
                navController.navigate("movieDetail/${selectedMovie.id}")
            }
        }
        // Movie Detail Screen showing details of the selected movie with original images
        composable(
            route = "movieDetail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            val selectedMovie = viewModel.movies.value.find { it.id == movieId }
            selectedMovie?.let { MovieDetailScreen(it, onBackPressed = { navController.popBackStack() }) }
        }
    }
}
