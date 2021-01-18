package com.example.myapplicationtest.net

import com.example.myapplicationtest.net.response.Response

class NetRepository {
    companion object {
        private const val API_KEY = "cc5502445713ffac4d7f9cd2493951c8"
    }

    private val apiMovies = RestWrapper.RestApi

    suspend fun getConfig() = apiMovies.loadConfiguration(API_KEY).checkOnErrors()

    suspend fun getGenres() = apiMovies.loadGenres(API_KEY)

    suspend fun getPlayingMovies() = apiMovies.loadPlayingMovie(API_KEY).checkOnErrors()

    suspend fun getPopularMovies() = apiMovies.loadPopularMovies(API_KEY).checkOnErrors()

    suspend fun getTopMovies() = apiMovies.loadTopMovies(API_KEY).checkOnErrors()

    suspend fun getUpcomingMovies() = apiMovies.loadUpcomingMovies(API_KEY).checkOnErrors()

    suspend fun getMovieDetails(id: Int) = apiMovies.loadMovieDetail(id, API_KEY).checkOnErrors()

    suspend fun getActorDetails(id: Int) = apiMovies.loadActorDetails(id, API_KEY).checkOnErrors()

    private fun <T : Response> T.checkOnErrors(): T {
        if (!this.success || this.status_code != 0)
            throw ApiException(status_code, status_message)
        return this
    }
}