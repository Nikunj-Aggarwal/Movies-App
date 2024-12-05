package com.practice.movies_app.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practice.movies_app.AppNavGraph
import com.practice.movies_app.activity.ui.theme.MoviesAppTheme
import com.practice.movies_app.viewmodel.MovieViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesAppTheme {
                //read access token: eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYTkxZmVlZjYxZDU5ZDQ0OGNlYTVmMDk1NWUxZTk2NCIsIm5iZiI6MTczMzM0OTUwMC43Miwic3ViIjoiNjc1MGQwN2NhOTNiZGVhOGMyNTBiNTYyIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.tNBRyPlR9ICJIV10gremsFzaDITjNeuTLTlQo8A-aqo
                val viewModel: MovieViewModel = viewModel()
                viewModel.fetchTrendingMovies("da91feef61d59d448cea5f0955e1e964")

                // Set up the navigation graph
                AppNavGraph(viewModel = viewModel)
            }

        }
    }
}
