package com.example.myapplicationtest.net.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class UpcomingMovies(

val dates: Dates,
val page: Long,
@SerialName("results")
val result: List<Result>,

@SerialName("total_pages")
val totalPages: Long,

@SerialName("total_results")
val totalResults: Long
):Response()

@kotlinx.serialization.Serializable
@SerialName("Dates")
data class Data (
    val maximum: String,
    val minimum: String
)

@Serializable
@SerialName("Result")
data class Resul (
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String,

    @SerialName("genre_ids")
    val genreIDS: List<Long>,

    val id: Long,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String,

    @SerialName("release_date")
    val releaseDate: String,

    val title: String,
    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Long
)

