package com.example.myapplicationtest.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationtest.business.LoadListMovie
import com.example.myapplicationtest.data.Movie
import com.example.myapplicationtest.interactor.LoadListMovieInteractor
import kotlinx.coroutines.launch

class MoviesViewModel() : ViewModel() {
    private val _liveData = MutableLiveData<List<Movie>>()
    val liveData: LiveData<List<Movie>> get() = _liveData
    private val _liveDataMovie = MutableLiveData<Movie>()
    val liveDataMovie: LiveData<Movie> get() = _liveDataMovie
    private var interactor: LoadListMovieInteractor = LoadListMovie()

    fun getListMovie(context: Context) {
        viewModelScope.launch {
            _liveData.postValue(interactor.loadListMovie(context))
        }
    }

    fun getItemMovie(id: Int) {
        viewModelScope.launch {
            _liveDataMovie.postValue(interactor.loadMovie(id))
        }
    }
}