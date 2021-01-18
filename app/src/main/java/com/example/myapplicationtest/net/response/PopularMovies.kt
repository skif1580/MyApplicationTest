package com.example.myapplicationtest.net.response

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@kotlinx.serialization.Serializable
data class PopularMovies(
val page: Long,
@SerialName("results")
val result: List<Result>,

@SerialName("total_results")
val totalResults: Long,

@SerialName("total_pages")
val totalPages: Long
):Response()

@kotlinx.serialization.Serializable
@SerialName("Result")
data class Reslt (
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




