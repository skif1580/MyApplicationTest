package com.example.myapplicationtest.business

import com.example.myapplicationtest.data.Movie
import com.example.myapplicationtest.interactor.LoadListMovieInteractor
import com.example.myapplicationtest.net.NetRepository
import com.example.myapplicationtest.net.response.*
import java.io.IOException

class LoadLDataMovieImpl(private val networkRepository :NetRepository) : LoadListMovieInteractor {
    private lateinit var configuration: Configuration
    private var genres: Gengers? = null

    override suspend fun loadConfig() {
        configuration = networkRepository.getConfig()
    }

    override suspend fun loadGenres() {
        genres = networkRepository.getGenres()
    }

    override suspend fun loadListPopularMovies(): List<Movie> =
        networkRepository.getPopularMovies().getListMovie()

    override suspend fun loadTopMovies(): List<Movie> =
        networkRepository.getTopMovies().getListMovie()

    override suspend fun loadPlaningMovies(): List<Movie> =
        networkRepository.getPlayingMovies().getListMovie()

    override suspend fun loadUpcomingMovies(): List<Movie> =
        networkRepository.getUpcomingMovies().getListMovie()

    override suspend fun loadMovieDetails(id: Int): MovieDetails =
        getMovieDetails(networkRepository.getMovieDetails(id))

    override suspend fun loadActorDetails(id: Int): ActorDetails =
        networkRepository.getActorDetails(id)

    private fun <T:Response> T.getListMovie(): List<Movie> =
        when (this) {
            is TopMovies -> {
                getMovie(this.result)
            }
            else -> {
                throw  Exception("")
            }
        }

    private fun getMovie(listRes: List<Resalt>): List<Movie> {
        val listMovie = mutableListOf<Movie>()
        listRes.forEach {
            val movie = Movie(
                configuration.images.baseURL + configuration.images.posterSizes[2] + it.posterPath,
                it.adult,
                it.overview,
                it.releaseDate,
                getGenres(it.genreIDS),
                it.id,
                it.originalTitle,
                it.originalLanguage,
                it.title,
                configuration.images.baseURL + configuration.images.backdropSizes[2] + it.backdropPath,
                it.popularity,
                it.voteCount,
                it.video,
                it.voteAverage
            )
            listMovie.add(movie)
        }
        return listMovie
    }

    private fun getMovieDetails(movie: MovieDetails): MovieDetails {
        val backgroundPath =
            configuration.images.baseURL + configuration.images.backdropSizes[2] + movie.backdropPath
        movie.backdropPath = backgroundPath
        return movie
    }

    private fun getGenres(genresId: List<Long>): List<String> {
        val list = mutableListOf<String>()
        for (index in genresId) {
            val data = genres?.genres?.filter { it.id == index }
            data?.forEach {
                list.add(it.name)
            }
        }
        return list
    }
}