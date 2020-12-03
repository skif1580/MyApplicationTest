package com.example.myapplicationtest.ui.fragment

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
import com.example.myapplicationtest.model.Actor
import com.example.myapplicationtest.model.Movies
import com.example.myapplicationtest.util.SpacesItemDecoration

class MovieDetailsFragment : Fragment() {

    private var tvNameMovie: TextView? = null
    private var tvAge: TextView? = null
    private var tvMovieTags: TextView? = null
    private var tvMovieCount: TextView? = null
    private var ivMovie: ImageView? = null

    private var listActor = mutableListOf<Actor>()
    private var rvActor: RecyclerView? = null
    private var movies: Movies? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listActor.add(Actor(resources.getString(R.string.movie_cast_1_name), R.drawable.robert))
        listActor.add(Actor(resources.getString(R.string.movie_cast_2_name), R.drawable.chris))
        listActor.add(Actor(resources.getString(R.string.movie_cast_3_name), R.drawable.mark))
        listActor.add(Actor(resources.getString(R.string.movie_cast_4_name), R.drawable.hemaworth))

        arguments?.let {
            movies = it.getSerializable("FILM") as Movies?
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
        tvNameMovie?.text = movies?.name
        tvAge = view.findViewById(R.id.movie_age_restrictions_text)
        tvAge?.text = movies?.movieYear
        tvMovieTags = view.findViewById(R.id.movie_tags_text)
        tvMovieTags?.text = movies?.tagMovie
        tvMovieCount = view.findViewById(R.id.movie_reviews_count_text)
        tvMovieCount?.text = movies?.movieCount
    }

    private fun initRecycler(view: View) {
        rvActor = view.findViewById(R.id.rv_actor)
        rvActor?.apply {
            adapter = ActorAdapter(listActor)
            addItemDecoration(SpacesItemDecoration(8))
            layoutManager =
                LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    companion object {

        fun newInstance(movies: Movies) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("FILM", movies)
                }
            }
    }
}