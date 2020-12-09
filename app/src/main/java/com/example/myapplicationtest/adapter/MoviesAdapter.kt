package com.example.myapplicationtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationtest.R
import com.example.myapplicationtest.data.Movie

class MoviesAdapter(private val listMovies: List<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    private var clickListenerCallback: ((movie: Movie) -> Unit)? = null

    fun clickListener(po: (movie: Movie) -> Unit) {
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
        private val ivMovieImage = item.findViewById<ImageView>(R.id.ivMoviePoster)
        private val ivMovieLike = item.findViewById<ImageView>(R.id.iv_like)
        private val ivStar1 = item.findViewById<ImageView>(R.id.star_movie_1)
        private val ivStar2 = item.findViewById<ImageView>(R.id.star_movie_2)
        private val ivStar3 = item.findViewById<ImageView>(R.id.star_movie_3)
        private val ivStar4 = item.findViewById<ImageView>(R.id.star_movie_4)
        private val ivStar5 = item.findViewById<ImageView>(R.id.star_movie_5)

        fun bin(movies: Movie) {
            val rating = getRatings(movies.ratings)
            tvMovieName.text = movies.title
            tvMovieTag.text = getMovieTeg(movies)
            tvMovieYear.text = movies.minimumAge.toString()
            tvMovieCount.text = movies.numberOfRatings.toString()
            tvMovieDuration.text = movies.runtime.toString()
            if (movies.poster.isEmpty()) {
                Glide.with(itemView)
                    .load(movies.poster)
                    .into(ivMovieImage)
            }
            when (rating) {

                1 -> {
                    ivStar2.setColorFilter(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.gray_dark
                        )
                    )
                    ivStar3.setColorFilter(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.gray_dark
                        )
                    )
                    ivStar4.setColorFilter(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.gray_dark
                        )
                    )
                    ivStar5.setColorFilter(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.gray_dark
                        )
                    )
                }

                2 -> {
                    ivStar3.setColorFilter(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.gray_dark
                        )
                    )
                    ivStar4.setColorFilter(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.gray_dark
                        )
                    )
                    ivStar5.setColorFilter(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.gray_dark
                        )
                    )
                }

                3 -> {
                    ivStar4.setColorFilter(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.gray_dark
                        )
                    )
                    ivStar5.setColorFilter(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.gray_dark
                        )
                    )
                }

                4 -> ivStar5.setColorFilter(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.gray_dark
                    )
                )
            }
        }

        private fun getRatings(ratings: Float): Int = (ratings / 2).toInt()

        private fun getMovieTeg(movie: Movie): String {
            val teg = mutableListOf<String>()
            for (id in movie.genres) {
                teg.add(id.name)
            }
            return teg.joinToString(",")
        }
    }
}