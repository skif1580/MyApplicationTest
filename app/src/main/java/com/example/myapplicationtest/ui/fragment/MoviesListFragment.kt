package com.example.myapplicationtest.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtest.R
import com.example.myapplicationtest.adapter.MoviesAdapter
import com.example.myapplicationtest.data.Movie
import com.example.myapplicationtest.viewmodel.MoviesViewModel
import com.example.myapplicationtest.viewmodel.State
import org.koin.android.ext.android.inject


class MoviesListFragment : Fragment() {

    private var clickListener: ClickMovies? = null
    private var rvMovies: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var moviesAdapter: MoviesAdapter? = null
    private val viewModel: MoviesViewModel by  inject()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickMovies) {
            clickListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTopMovies()
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
        viewModel.stateLiveData.observe(viewLifecycleOwner, this::setState)
    }

    private fun setState(state: State) =
        when (state) {
            is State.Default -> {
                showDefaultState()
            }
            is State.Loading -> {
                showLoadState()
            }
            is State.Success -> {
                showDataState(state.listMovie)
            }
            is State.Error -> {
                showErrorMessageState(state.error)
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
        progressBar = view.findViewById(R.id.pbLoad)
    }

    private fun showDefaultState() {
        progressBar?.isVisible = false
        rvMovies?.isVisible = false
    }

    private fun showLoadState() {
        progressBar?.isVisible = true
        rvMovies?.isVisible = false
    }

    private fun showDataState(listMovie: List<Movie>) {
        progressBar?.visibility = GONE
        rvMovies?.isVisible = true
        moviesAdapter?.swapData(listMovie)
    }

    private fun showErrorMessageState(pair: Pair<Int, String>) {
        progressBar?.visibility = GONE
        rvMovies?.isVisible = false
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
    fun clickMoviesListener(id: Int)
}