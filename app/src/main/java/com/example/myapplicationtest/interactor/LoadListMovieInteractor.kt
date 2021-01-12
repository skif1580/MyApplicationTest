package com.example.myapplicationtest.interactor

import com.example.myapplicationtest.data.Movie
import com.example.myapplicationtest.net.response.ActorDetails
import com.example.myapplicationtest.net.response.MovieDetails

interface LoadListMovieInteractor {
    suspend fun loadConfig()
    suspend fun loadGenres()
    suspend fun loadListPopularMovies(): List<Movie>
    suspend fun loadTopMovies():List<Movie>
    suspend fun loadPlaningMovies():List<Movie>
    suspend fun loadUpcomingMovies():List<Movie>
    suspend fun loadMovieDetails(id:Int):MovieDetails
    suspend fun loadActorDetails(id:Int):ActorDetails

}