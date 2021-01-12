package com.example.myapplicationtest.data

data class Movie(
    val posterPath: String,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String,
    val genreIDS: List<String>,
    val id: Long,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backdropPath: String,
    val popularity: Double,
    val voteCount: Long,
    val video: Boolean,
    val voteAverage: Double
)