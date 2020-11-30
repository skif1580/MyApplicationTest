package com.example.myapplicationtest.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.myapplicationtest.R


class MoviesListFragment : Fragment() {

    private var imageMovies: ImageView? = null
    private var clickListener: ClickMovies? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        initImageView(view)
    }

    private fun initImageView(view: View) {
        imageMovies = view.findViewById(R.id.ivMoviesList)
        imageMovies?.setOnClickListener {
            clickListener?.clickMoviesListener()
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
    fun clickMoviesListener()
}