package com.example.myapplicationtest.net.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

    @Serializable
    data class Gengers (
val genres: List<Genre>
):Response()

@Serializable
@SerialName("Genre")
data class Genrers (
    val id: Long,
    val name: String
)

