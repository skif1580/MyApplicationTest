package com.example.myapplicationtest.net.response

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class MoviePlaying(
    val page: Long,
    @SerialName("results")
    val result: List<Result>,
    val dates: Dates,

    @SerialName("total_pages")
    val totalPages: Long,

    @SerialName("total_results")
    val totalResults: Long
):Response()

@kotlinx.serialization.Serializable
data class Dates(
    val maximum: String,
    val minimum: String
)

@kotlinx.serialization.Serializable
data class Result(
    @SerialName("poster_path")
    val posterPath: String,

    val adult: Boolean,
    val overview: String,

    @SerialName("release_date")
    val releaseDate: String,

    @SerialName("genre_ids")
    val genreIDS: List<Long>,

    val id: Long,

    @SerialName("original_title")
    val originalTitle: String,

    @SerialName("original_language")
    val originalLanguage: String,

    val title: String,

    @SerialName("backdrop_path")
    val backdropPath: String,

    val popularity: Double,

    @SerialName("vote_count")
    val voteCount: Long,

    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double
)



