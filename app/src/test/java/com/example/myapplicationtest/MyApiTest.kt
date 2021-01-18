package com.example.myapplicationtest

import com.example.myapplicationtest.net.ApiMovies
import com.example.myapplicationtest.net.NetRepository
import com.example.myapplicationtest.net.RestWrapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MyApiTest {
    val API_KEY = "cc5502445713ffac4d7f9cd2493951c8"
    val api = RestWrapper.RestApi
    val netRepository = NetRepository()

    @Test
    fun testConfig() = runBlocking {
        val conf = api.loadConfiguration(API_KEY)
        Assert.assertNotNull(conf)
    }

    @Test
    fun testLoadPlayingMovie() = runBlocking {
        val res = api.loadPlayingMovie(API_KEY)
        Assert.assertNotNull(res)
    }

    @Test
    fun testLoadPopularMovies() = runBlocking {
        val res = api.loadPopularMovies(API_KEY)
        Assert.assertNotNull(res)
    }

    @Test
    fun testLoadTopMovies() = runBlocking {
       val data= netRepository.getTopMovies()
       // val res = api.loadTopMovies(API_KEY)
        Assert.assertNotNull(data)
    }

    @Test
    fun testLoadUpcomingMovies() = runBlocking {
        val res = api.loadUpcomingMovies(API_KEY)
        Assert.assertNotNull(res)
    }

    @Test
    fun testLoadMovieDetail() = runBlocking {
        val res = api.loadMovieDetail(445, API_KEY)
        Assert.assertNotNull(res)
    }

    @Test
    fun testLoadActorDetails() = runBlocking {
        val res = api.loadActorDetails(666, API_KEY)
        Assert.assertNotNull(res)
    }
}