package com.example.myapplicationtest.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplicationtest.R
import com.example.myapplicationtest.extends.setTintColor
import com.example.myapplicationtest.net.response.MovieDetails
import com.example.myapplicationtest.viewmodel.DetailsViewModel
import com.example.myapplicationtest.viewmodel.StateDetails
import org.koin.android.ext.android.inject
import kotlin.math.roundToInt

class MovieDetailsFragment : Fragment() {

    companion object {
        private const val KEY = "Movie"

        fun newInstance(id: Int) = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY, id)
            }
        }
    }

    private var tvNameMovie: TextView? = null
    private var tvAge: TextView? = null
    private var tvMovieTags: TextView? = null
    private var tvMovieCount: TextView? = null
    private var tvMovieStorylineText: TextView? = null
    private var progressBar: ProgressBar? = null
    private var rvActor: RecyclerView? = null
    private var ivMovie: ImageView? = null
    private var ratingStar1: ImageView? = null
    private var ratingStar2: ImageView? = null
    private var ratingStar3: ImageView? = null
    private var ratingStar4: ImageView? = null
    private var ratingStar5: ImageView? = null
    private val detailsViewModel: DetailsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.getInt(KEY)
        if (id != null) {
            detailsViewModel.getMovieDetails(id)
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
        initUi(view)
        //initRecycler(view)
        detailsViewModel.stateLiveData.observe(viewLifecycleOwner, this::setState)
        // movies?.actors?.let { actorAdapter?.swapData(it) }
    }

    private fun setState(state: StateDetails) {
        when (state) {
            is StateDetails.Default -> {
                showDefaultState()
            }
            is StateDetails.LoadData -> {
                showLoadingState()
            }
            is StateDetails.Success -> {
                showSuccessState(state.data)
                setColorRatingStar(state.data)
            }
            is StateDetails.Error -> {
                showErrorState(state.error)
            }
        }
    }

    private fun showDefaultState() {
        ratingStar1?.isVisible = false
        ratingStar2?.isVisible = false
        ratingStar3?.isVisible = false
        ratingStar4?.isVisible = false
        ratingStar5?.isVisible = false
        progressBar?.isVisible = false
        tvAge?.isVisible = false
        tvMovieStorylineText?.isVisible = false
        tvMovieCount?.isVisible = false
        tvMovieTags?.isVisible = false
        tvNameMovie?.isVisible = false
        ivMovie?.isVisible = false
    }

    private fun showLoadingState() {
        progressBar?.isVisible = true
        ratingStar1?.isVisible = false
        ratingStar2?.isVisible = false
        ratingStar3?.isVisible = false
        ratingStar4?.isVisible = false
        ratingStar5?.isVisible = false
        tvAge?.isVisible = false
        tvMovieStorylineText?.isVisible = false
        tvMovieCount?.isVisible = false
        tvMovieTags?.isVisible = false
        tvNameMovie?.isVisible = false
        ivMovie?.isVisible = false
    }

    @SuppressLint("SetTextI18n")
    private fun showSuccessState(movie: MovieDetails) {
        progressBar?.visibility = GONE
        ratingStar1?.isVisible = true
        ratingStar2?.isVisible = true
        ratingStar3?.isVisible = true
        ratingStar4?.isVisible = true
        ratingStar5?.isVisible = true
        tvAge?.isVisible = true
        if (movie.adult) {
            tvAge?.text = "%s+".format(13.toString())
        } else {
            tvAge?.text = "16+"
        }
        tvMovieStorylineText?.isVisible = true
        tvMovieStorylineText?.text = movie.overview
        tvMovieCount?.isVisible = true
        tvMovieCount?.text = "%s Reviews".format(movie.voteCount.toString())
        tvMovieTags?.isVisible = true
        tvMovieTags?.text = getMovieTeg(movie)
        tvNameMovie?.isVisible = true
        tvNameMovie?.text = movie.title
        ivMovie?.isVisible = true
        ivMovie?.load(movie.backdropPath)
    }

    private fun showErrorState(error: Pair<Int, String>) {
        progressBar?.visibility = GONE
        ratingStar1?.isVisible = false
        ratingStar2?.isVisible = false
        ratingStar3?.isVisible = false
        ratingStar4?.isVisible = false
        ratingStar5?.isVisible = false
        progressBar?.isVisible = false
        tvAge?.isVisible = false
        tvMovieStorylineText?.isVisible = false
        tvMovieCount?.isVisible = false
        tvMovieTags?.isVisible = false
        tvNameMovie?.isVisible = false
        ivMovie?.isVisible = false
    }

    private fun setColorRatingStar(movies: MovieDetails) {
        val rating = movies.voteAverage.roundToInt() / 2
        when (rating) {
            1 -> {
                ratingStar2?.setTintColor(R.color.gray_dark)
                ratingStar3?.setTintColor(R.color.gray_dark)
                ratingStar4?.setTintColor(R.color.gray_dark)
                ratingStar5?.setTintColor(R.color.gray_dark)
            }

            2 -> {
                ratingStar3?.setTintColor(R.color.gray_dark)
                ratingStar4?.setTintColor(R.color.gray_dark)
                ratingStar5?.setTintColor(R.color.gray_dark)
            }
            3 -> {
                ratingStar4?.setTintColor(R.color.gray_dark)
                ratingStar5?.setTintColor(R.color.gray_dark)
            }
            4 -> {
                ratingStar5?.setTintColor(R.color.gray_dark)
            }
            5 -> {
            }
        }
    }

    private fun initUi(view: View) {
        progressBar = view.findViewById(R.id.pb_details_movie)
        tvNameMovie = view.findViewById(R.id.movie_name_text)
        tvAge = view.findViewById(R.id.movie_age_restrictions_text)
        tvMovieTags = view.findViewById(R.id.movie_tags_text)
        tvMovieStorylineText = view.findViewById(R.id.movie_storyline_text)
        tvMovieCount = view.findViewById(R.id.movie_reviews_count_text)
        ivMovie = view.findViewById(R.id.movie_logo_image)
        ratingStar1 = view.findViewById(R.id.movie_rating_star1_image)
        ratingStar2 = view.findViewById(R.id.movie_rating_star2_image)
        ratingStar3 = view.findViewById(R.id.movie_rating_star3_image)
        ratingStar4 = view.findViewById(R.id.movie_rating_star4_image)
        ratingStar5 = view.findViewById(R.id.movie_rating_star5_image)
    }

    private fun getRatings(ratings: Float): Int = ratings.roundToInt() / 2

    private fun getMovieTeg(movies: MovieDetails): String =
        movies.genres.map { it.name }.toList().joinToString(", ")

//    private fun initRecycler(view: View) {
//        rvActor = view.findViewById(R.id.rv_actor)
//        if (movies?.actors?.isNotEmpty() == true) {
//            rvActor?.visibility = View.VISIBLE
//            rvActor?.apply {
//                // addItemDecoration(SpacesItemDecoration(8))
//                layoutManager =
//                    LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
//                adapter = actorAdapter
//            }
//        } else {
//            rvActor?.visibility = View.GONE
//        }
//    }
}