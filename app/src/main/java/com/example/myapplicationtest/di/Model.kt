package com.example.myapplicationtest.di

import com.example.myapplicationtest.business.LoadLDataMovie
import com.example.myapplicationtest.net.NetRepository
import com.example.myapplicationtest.viewmodel.DetailsViewModel
import com.example.myapplicationtest.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moviesModule = module {
    single { NetRepository() }
    single { LoadLDataMovie(get()) }
    viewModel { MoviesViewModel(get()) }
}

val detailsModule = module {
viewModel { DetailsViewModel(get()) }
}