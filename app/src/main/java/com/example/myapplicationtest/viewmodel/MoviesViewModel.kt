package com.example.myapplicationtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationtest.data.Movie
import com.example.myapplicationtest.interactor.LoadListMovieInteractor
import com.example.myapplicationtest.net.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MoviesViewModel(private val interactor: LoadListMovieInteractor) : ViewModel() {
    private val _stateLiveData = MutableLiveData<State>(State.Default())
    val stateLiveData: LiveData<State> get() = _stateLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.loadConfig()
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            _stateLiveData.postValue(State.Loading())
            try {
                val res = interactor.loadListPopularMovies()
                if (res != null)
                    _stateLiveData.postValue(State.Success(res))
            } catch (e: UnknownHostException) {
                _stateLiveData.postValue(State.Error(999 to "Connection failed"))
            } catch (e: ApiException) {
                val code = e.code
                val msg = e.msg
                _stateLiveData.postValue(State.Error(code to msg))
            }
        }
    }

    fun getTopMovies() {
        viewModelScope.launch {
            interactor.loadGenres()
            _stateLiveData.postValue(State.Loading())
            try {
                val data = interactor.loadTopMovies()
                _stateLiveData.postValue(State.Success(data))
            } catch (e: UnknownHostException) {
            } catch (e: ApiException) {
                val code = e.code
                val msg = e.msg
            }
        }
    }

    fun getPlaningMovies() {
        viewModelScope.launch {
            try {

            } catch (e: UnknownHostException) {

            } catch (e: ApiException) {
                val code = e.code
                val msg = e.msg

            }
        }
    }

    fun getUpcomingMovies() {
        viewModelScope.launch {
            try {

            } catch (e: UnknownHostException) {

            } catch (e: ApiException) {
                val code = e.code
                val msg = e.msg

            }
        }
    }

}

sealed class State {
    class Default : State()
    class Loading() : State()
    data class Success(val listMovie: List<Movie>) : State()
    data class Error(val error: Pair<Int, String>) : State()
}