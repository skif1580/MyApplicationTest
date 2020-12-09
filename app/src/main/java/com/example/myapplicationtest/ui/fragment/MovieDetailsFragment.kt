package com.example.myapplicationtest.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationtest.R
import com.example.myapplicationtest.adapter.ActorAdapter
import com.example.myapplicationtest.data.Actor
import com.example.myapplicationtest.data.Movie
import com.example.myapplicationtest.util.SpacesItemDecoration

class MovieDetailsFragment : Fragment() {

    private var tvNameMovie: TextView? = null
    private var tvAge: TextView? = null
    private var tvMovieTags: TextView? = null
    private var tvMovieCount: TextView? = null
    private var rvActor: RecyclerView? = null
    private var ivMovie: ImageView? = null
    private var ivStar: ImageView? = null
    private var movies: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movies = it.getSerializable(KEY) as Movie?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(view)
        initUi(view)
    }

    private fun initUi(view: View) {
        tvNameMovie = view.findViewById(R.id.movie_name_text)
        tvNameMovie?.text = movies?.title
        tvAge = view.findViewById(R.id.movie_age_restrictions_text)
        tvAge?.text = movies?.minimumAge?.toString()
        tvMovieTags = view.findViewById(R.id.movie_tags_text)
        tvMovieTags?.text = getMovieTeg(movies!!)
        tvMovieCount = view.findViewById(R.id.movie_reviews_count_text)
        tvMovieCount?.text = movies?.numberOfRatings?.toString()
        ivStar = view.findViewById(R.id.movie_rating_star5_image)
        ivMovie = view.findViewById(R.id.movie_logo_image)
        Glide.with(view.context)
            .load(movies?.backdrop)
            .into(ivMovie!!)
    }

    private fun initRecycler(view: View) {
        rvActor = view.findViewById(R.id.rv_actor)
        if (movies?.actors?.isEmpty() == true) {
            rvActor?.visibility = GONE
        } else {
            rvActor?.apply {
                adapter = movies?.actors?.let { ActorAdapter(it) }
                addItemDecoration(SpacesItemDecoration(8))
                layoutManager =
                    LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    private fun getMovieTeg(movie: Movie): String {
        val teg = mutableListOf<String>()
        for (id in movie.genres) {
            teg.add(id.name)
        }
        return teg.joinToString(",")
    }

    companion object {
        private const val KEY = "Movie"

        fun newInstance(movies: Movie) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY, movies)
                }
            }
    }
}