package com.example.mybookmyshow.APIInterface

import com.example.mybookmyshow.DataClass.MovieData.MovieAPIData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Movie {

    @GET("/3/movie/{movie_id}")
    fun getMovie(@Path("movie_id") movie_id:Int, @Query("api_key") key:String): Call<MovieAPIData>
}