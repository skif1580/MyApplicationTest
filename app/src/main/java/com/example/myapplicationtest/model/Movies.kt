package com.example.myapplicationtest.model

import java.io.Serializable

data class Movies (
    val name: String,
    val tagMovie: String,
    val movieYear: String,
    val movieCount: String,
    val movieDuration: String,
    val movieImage: Int,
    val countStar: Int,
    val movieLike: Boolean
) :Serializable {
}