package com.example.myapplicationtest.net

import com.example.myapplicationtest.net.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMovies {

    @GET("configuration")
    suspend fun loadConfiguration(@Query("api_key") key: String): Configuration

    @GET("movie/now_playing")
    suspend fun loadPlayingMovie(
        @Query("api_key") key: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MoviePlaying

    @GET("movie/popular")
    suspend fun loadPopularMovies(
        @Query("api_key") key: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): PopularMovies

    @GET("movie/top_rated")
    suspend fun loadTopMovies(
        @Query("api_key") key: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): TopMovies

    @GET("movie/upcoming")
    suspend fun loadUpcomingMovies(
        @Query("api_key") key: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): UpcomingMovies

    @GET("movie/{movie_id}")
    suspend fun loadMovieDetail(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") key: String,
        @Query("language") language: String = "en-US"
    ): MovieDetails

    @GET("person/{person_id}")
    suspend fun loadActorDetails(
        @Path("person_id") person_id: Int,
        @Query("api_key") key: String,
        @Query("language") language: String = "en-US"
    ): ActorDetails

    @GET("genre/movie/list")
    suspend fun loadGenres(
        @Query("api_key") key: String,
        @Query("language") language: String = "en-US"
    ):Gengers
}