package com.example.myapplicationtest.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtest.R
import com.example.myapplicationtest.adapter.MoviesAdapter
import com.example.myapplicationtest.data.Movie
import com.example.myapplicationtest.data.loadMovies
import kotlinx.coroutines.*


class MoviesListFragment : Fragment() {

    private var clickListener: ClickMovies? = null
    private var rvMovies: RecyclerView? = null
    private var listMovies = listOf<Movie>()
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickMovies) {
            clickListener = context
        }
        scope.launch {
            listMovies = loadMovies(context)
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
            // addItemDecoration(SpacesItemDecorationMovies(11, 15))
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
        scope.cancel()
    }
}

interface ClickMovies {
    fun clickMoviesListener(movies: Movie)
}