package com.example.myapplicationtest.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtest.R
import com.example.myapplicationtest.adapter.MoviesAdapter
import com.example.myapplicationtest.data.Movie
import com.example.myapplicationtest.data.loadMovies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class MoviesListFragment : Fragment() {

    private var clickListener: ClickMovies? = null
    private var rvMovies: RecyclerView? = null
    private var moviesAdapter: MoviesAdapter? = null
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickMovies) {
            clickListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesAdapter = MoviesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi(view)
        scope.launch {
            moviesAdapter?.swapData(loadMovies(view.context))
        }
    }

    private fun initUi(view: View) {
        rvMovies = view.findViewById(R.id.rvMovies)
        rvMovies?.apply {
            setHasFixedSize(true)
            // addItemDecoration(SpacesItemDecorationMovies(11, 15))
            adapter = moviesAdapter
            layoutManager = GridLayoutManager(view.context, 2, GridLayoutManager.VERTICAL, false)
        }
        moviesAdapter?.clickListener {
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