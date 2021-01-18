package com.example.myapplicationtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationtest.interactor.LoadListMovieInteractor
import com.example.myapplicationtest.net.ApiException
import com.example.myapplicationtest.net.response.MovieDetails
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class DetailsViewModel( private val interactor: LoadListMovieInteractor) : ViewModel() {
    private var _stateLiveData = MutableLiveData<StateDetails>(StateDetails.Default)
    val stateLiveData: LiveData<StateDetails> get() = _stateLiveData

    init {
        viewModelScope.launch {
            interactor.loadConfig()
        }
    }

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            _stateLiveData.postValue(StateDetails.LoadData)
            try {
            _stateLiveData.postValue(StateDetails.Success(interactor.loadMovieDetails(id)))
            } catch (e: UnknownHostException) {
                _stateLiveData.postValue(StateDetails.Error(999 to "Connection failed"))
            } catch (e: ApiException) {
                val code = e.code
                val msg = e.msg
                _stateLiveData.postValue(StateDetails.Error(code to msg))
            }
        }
    }

    fun getActorDetail(id: Int) {
        viewModelScope.launch {

        }
    }
}

sealed class StateDetails {
    object Default : StateDetails()
    object LoadData : StateDetails()
    data class Success(val data: MovieDetails) : StateDetails()
    data class Error(val error: Pair<Int, String>) : StateDetails()
}