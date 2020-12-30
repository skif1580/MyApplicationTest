package com.example.myapplicationtest.interactor

import android.content.Context
import com.example.myapplicationtest.data.Movie

interface LoadListMovieInteractor {
    suspend fun loadListMovie(context: Context): List<Movie>
    suspend fun loadMovie(id: Int): Movie?
}