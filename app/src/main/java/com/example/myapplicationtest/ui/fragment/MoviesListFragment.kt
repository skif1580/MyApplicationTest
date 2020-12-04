package com.example.myapplicationtest.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtest.R
import com.example.myapplicationtest.adapter.MoviesAdapter
import com.example.myapplicationtest.model.Movies
import com.example.myapplicationtest.util.SpacesItemDecorationMovies


class MoviesListFragment : Fragment() {

    private var clickListener: ClickMovies? = null
    private var rvMovies: RecyclerView? = null
    private var listMovies = mutableListOf<Movies>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listMovies.add(
            Movies(
                resources.getString(R.string.avengers_movie_name),
                resources.getString(R.string.avengers_movie_tags),
                "13+",
                "125 Reviews",
                "137 MIN",
                R.drawable.movie,
                4,
                false
            )
        )

        listMovies.add(
            Movies(
                resources.getString(R.string.movie_name_2),
                resources.getString(R.string.movies_tags_2),
                "16+",
                "98 Reviews",
                "97 MIN",
                R.drawable.tenet,
                5,
                true
            )
        )

        listMovies.add(
            Movies(
                resources.getString(R.string.movie_name_3),
                resources.getString(R.string.movies_tags_3),
                "13+",
                "38 Reviews",
                "102 MIN",
                R.drawable.black_widow,
                4,
                false
            )
        )

        listMovies.add(
            Movies(
                resources.getString(R.string.movie_name_4),
                resources.getString(R.string.movies_tags_4),
                "13+",
                "74 Reviews",
                "120 MIN",
                R.drawable.wonder_woman,
                5,
                false
            )
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickMovies) {
            clickListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val moviesAdapter = MoviesAdapter(listMovies)
        initUi(view, moviesAdapter)
    }

    private fun initUi(view: View, adapter: MoviesAdapter) {
        rvMovies = view.findViewById(R.id.rvMovies)
        rvMovies?.apply {
            setHasFixedSize(true)
            addItemDecoration(SpacesItemDecorationMovies(11, 15))
            this.adapter = adapter
            layoutManager = GridLayoutManager(view.context, 2, GridLayoutManager.VERTICAL, false)
        }
        adapter.clickListener {
            clickListener?.clickMoviesListener(it)
        }
    }

    companion object {
        fun newInstance() =
            MoviesListFragment()
    }

    override fun onDetach() {
        super.onDetach()
        clickListener = null
    }
}

interface ClickMovies {
    fun clickMoviesListener(movies: Movies)
}