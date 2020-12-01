package com.example.myapplicationtest.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtest.databinding.ItemMoviesListBinding
import com.example.myapplicationtest.model.Movies

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {
    private var listMovies = listOf<Movies>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = listMovies.size

    class MoviesHolder(private val itemMovies: ItemMoviesListBinding) :
        RecyclerView.ViewHolder(itemMovies.root) {}
}