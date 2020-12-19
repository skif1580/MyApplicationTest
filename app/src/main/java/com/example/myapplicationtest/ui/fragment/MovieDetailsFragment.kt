package com.example.myapplicationtest.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationtest.R
import com.example.myapplicationtest.adapter.ActorAdapter
import com.example.myapplicationtest.data.Movie
import com.example.myapplicationtest.extends.setTintColor
import kotlin.math.roundToInt

class MovieDetailsFragment : Fragment() {

    private var tvNameMovie: TextView? = null
    private var tvAge: TextView? = null
    private var tvMovieTags: TextView? = null
    private var tvMovieCount: TextView? = null
    private var tvMovieStorylineText: TextView? = null
    private var rvActor: RecyclerView? = null
    private var ivMovie: ImageView? = null
    private var ratingStar1: ImageView? = null
    private var ratingStar2: ImageView? = null
    private var ratingStar3: ImageView? = null
    private var ratingStar4: ImageView? = null
    private var ratingStar5: ImageView? = null
    private var movies: Movie? = null
    private var actorAdapter: ActorAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movies = it.getSerializable(KEY) as Movie?
        }
        actorAdapter = ActorAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi(view)
        initRecycler(view)
        initRatingStar(view, movies)
        movies?.actors?.let { actorAdapter?.swapData(it) }
    }

    private fun initRatingStar(view: View, movies: Movie?) {
        val rating = movies?.ratings?.let { getRatings(it) }

        ratingStar1 = view.findViewById(R.id.movie_rating_star1_image)
        ratingStar2 = view.findViewById(R.id.movie_rating_star2_image)
        ratingStar3 = view.findViewById(R.id.movie_rating_star3_image)
        ratingStar4 = view.findViewById(R.id.movie_rating_star4_image)
        ratingStar5 = view.findViewById(R.id.movie_rating_star5_image)

        when (rating) {
            1 -> {
                ratingStar2?.setTintColor(view.context, R.color.gray_dark)
                ratingStar3?.setTintColor(view.context, R.color.gray_dark)
                ratingStar4?.setTintColor(view.context, R.color.gray_dark)
                ratingStar5?.setTintColor(view.context, R.color.gray_dark)
            }

            2 -> {
                ratingStar3?.setTintColor(view.context, R.color.gray_dark)
                ratingStar4?.setTintColor(view.context, R.color.gray_dark)
                ratingStar5?.setTintColor(view.context, R.color.gray_dark)
            }
            3 -> {
                ratingStar4?.setTintColor(view.context, R.color.gray_dark)
                ratingStar5?.setTintColor(view.context, R.color.gray_dark)
            }
            4 -> {
                ratingStar4?.setTintColor(view.context, R.color.gray_dark)
                ratingStar5?.setTintColor(view.context, R.color.gray_dark)
            }
            5 -> {
                ratingStar5?.setTintColor(view.context, R.color.gray_dark)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initUi(view: View) {
        tvNameMovie = view.findViewById(R.id.movie_name_text)
        tvNameMovie?.text = movies?.title

        tvAge = view.findViewById(R.id.movie_age_restrictions_text)
        tvAge?.text = "%s +".format(movies?.minimumAge.toString())

        tvMovieTags = view.findViewById(R.id.movie_tags_text)
        tvMovieTags?.text = getMovieTeg(movies!!)

        tvMovieStorylineText = view.findViewById(R.id.movie_storyline_text)
        tvMovieStorylineText?.text = movies?.overview

        tvMovieCount = view.findViewById(R.id.movie_reviews_count_text)
        tvMovieCount?.text = "%s Reviews".format(movies?.numberOfRatings.toString())

        ivMovie = view.findViewById(R.id.movie_logo_image)
        Glide.with(view.context)
            .load(movies?.backdrop)
            .into(ivMovie!!)
    }

    private fun getRatings(ratings: Float): Int = ratings.roundToInt() / 2

    private fun getMovieTeg(movies: Movie): String {
        val tegGenres = mutableListOf<String>()
        for (teg in movies.genres) {
            tegGenres.add(teg.name)
        }
        return tegGenres.joinToString(",")
    }

    private fun initRecycler(view: View) {
        rvActor = view.findViewById(R.id.rv_actor)
        if (movies?.actors?.isNotEmpty() == true) {
            rvActor?.visibility = View.VISIBLE
            rvActor?.apply {
                // addItemDecoration(SpacesItemDecoration(8))
                layoutManager =
                    LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = actorAdapter
            }
        } else {
            rvActor?.visibility = View.GONE
        }
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