package com.example.mybookmyshow.APIInterface

import com.example.mybookmyshow.DataClass.TopRated.TopRatedAPIData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RelatedMovies {

    @GET("/3/movie/{movie_id}/similar")
    fun getSimilar(@Path("movie_id") movieId:Int, @Query("api_key") key: String): Call<TopRatedAPIData>
}