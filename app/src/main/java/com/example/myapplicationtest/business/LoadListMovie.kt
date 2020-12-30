package com.example.myapplicationtest.business

import android.content.Context
import com.example.myapplicationtest.data.Movie
import com.example.myapplicationtest.data.loadMovies
import com.example.myapplicationtest.interactor.LoadListMovieInteractor

class LoadListMovie : LoadListMovieInteractor {
    private var list = listOf<Movie>()
    override suspend fun loadListMovie(context: Context): List<Movie> {
        list = loadMovies(context)
        return list
    }

    override suspend fun loadMovie(id: Int): Movie? {
        var movie: Movie? = null
        list.forEach {
            if (it.id == id)
                movie = it
        }
        return movie
    }
}