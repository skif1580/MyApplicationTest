package com.example.myapplicationtest.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.myapplicationtest.R
import com.example.myapplicationtest.model.Movies
import com.example.myapplicationtest.ui.fragment.ClickMovies
import com.example.myapplicationtest.ui.fragment.MovieDetailsFragment
import com.example.myapplicationtest.ui.fragment.MoviesListFragment

class MainActivity : AppCompatActivity(), ClickMovies {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, MoviesListFragment.newInstance())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
    }

    override fun clickMoviesListener(movies: Movies) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, MovieDetailsFragment.newInstance(movies))
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack("panel")
            .commit()
    }
}