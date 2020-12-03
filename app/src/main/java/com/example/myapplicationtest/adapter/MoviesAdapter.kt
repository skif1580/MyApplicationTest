package com.example.myapplicationtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationtest.R
import com.example.myapplicationtest.model.Movies

class MoviesAdapter(private val listMovies: List<Movies>) :
    RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    private var clickListenerCallback: ((movie: Movies) -> Unit)? = null

    fun clickListener(po: (movie: Movies) -> Unit) {
        clickListenerCallback = po
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies_list, parent, false)
        return MoviesHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.bin(listMovies[position])
        holder.itemView.setOnClickListener {
            clickListenerCallback?.invoke(listMovies[position])
        }
    }

    override fun getItemCount(): Int = listMovies.size

    class MoviesHolder(item: View) :
        RecyclerView.ViewHolder(item) {

        private val tvMovieName = item.findViewById<TextView>(R.id.tv_movie_name)
        private val tvMovieTag = item.findViewById<TextView>(R.id.tv_movie_tags)
        private val tvMovieYear = item.findViewById<TextView>(R.id.tv_year)
        private val tvMovieCount = item.findViewById<TextView>(R.id.tv_movie_reviews_count)
        private val tvMovieDuration = item.findViewById<TextView>(R.id.tv_movie_duration)
        private val ivMovieImage = item.findViewById<ImageView>(R.id.iv_movie_image)
        private val ivMovieLike = item.findViewById<ImageView>(R.id.iv_like)

        fun bin(movies: Movies) {
            tvMovieName.text = movies.name
            tvMovieTag.text = movies.tagMovie
            tvMovieYear.text = movies.movieYear
            tvMovieCount.text = movies.movieCount
            tvMovieDuration.text = movies.movieDuration
            if (movies.movieLike) {
                ivMovieLike.setColorFilter(R.color.pink_light)
            }
            Glide.with(itemView)
                .load(movies.movieImage)
                .into(ivMovieImage)
        }
    }
}