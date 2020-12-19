package com.example.myapplicationtest.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myapplicationtest.R
import com.example.myapplicationtest.data.Movie
import com.example.myapplicationtest.extends.setTintColor
import kotlin.math.roundToInt

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    private var items = listOf<Movie>()
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
        holder.bin(items[position])
        holder.itemView.setOnClickListener {
            clickListenerCallback?.invoke(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    fun swapData(newData: List<Movie>) {
        this.items = newData
        notifyDataSetChanged()
    }

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

        @SuppressLint("SetTextI18n")
        fun bin(movies: Movie) {
            val rating = getRatings(movies.ratings)
            tvMovieName.text = movies.title
            tvMovieTag.text = getMovieTeg(movies)
            tvMovieYear.text = "%s+".format(movies.minimumAge.toString())
            tvMovieCount.text = "%s Reviews".format(movies.numberOfRatings.toString())
            tvMovieDuration.text = "%s  MIN".format(movies.runtime.toString())

            val transform = MultiTransformation(CenterCrop(),GranularRoundedCorners(16f, 16f,0f,0f))
            Glide.with(itemView)
                .load(movies.poster)
                .apply(RequestOptions.bitmapTransform(transform))
                .into(ivMovieImage)

            when (rating) {

                1 -> {
                    ivStar2.setTintColor(itemView.context,R.color.gray_dark)
                    ivStar3.setTintColor(itemView.context,R.color.gray_dark)
                    ivStar4.setTintColor(itemView.context,R.color.gray_dark)
                    ivStar5.setTintColor(itemView.context,R.color.gray_dark)
                }

                2 -> {
                    ivStar3.setTintColor(itemView.context,R.color.gray_dark)
                    ivStar4.setTintColor(itemView.context,R.color.gray_dark)
                    ivStar5.setTintColor(itemView.context,R.color.gray_dark)
                }

                3 -> {
                    ivStar4.setTintColor(itemView.context,R.color.gray_dark)
                    ivStar5.setTintColor(itemView.context,R.color.gray_dark)
                }

                4 -> ivStar5.setTintColor(itemView.context,R.color.gray_dark)
            }
        }

        private fun getRatings(ratings: Float): Int = ratings.roundToInt() / 2

        private fun getMovieTeg(movies: Movie): String {
            val tegGenres = mutableListOf<String>()
            for (teg in movies.genres){
                tegGenres.add(teg.name)
            }
            return tegGenres.joinToString (", ")
        }
    }
}