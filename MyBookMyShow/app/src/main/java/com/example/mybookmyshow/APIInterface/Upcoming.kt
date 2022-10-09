package com.example.mybookmyshow.APIInterface

import com.example.mybookmyshow.DataClass.TopRated.TopRatedAPIData
import com.example.mybookmyshow.DataClass.UpcomingMovies.UpcomingMovieAPIData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Upcoming {

    @GET("/3/movie/upcoming")
    fun getUpcoming(@Query("api_key") key: String,@Query("region") region:String = "IN",@Query("with_release_type") releaseType:String="2|3"): Call<UpcomingMovieAPIData>
}