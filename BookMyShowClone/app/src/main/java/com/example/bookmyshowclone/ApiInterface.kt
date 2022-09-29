package com.example.bookmyshowclone

import com.example.bookmyshowclone.Top_rated_Data.TopRatedMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/3/movie/top_rated")
    fun getData(@Query("api_key") key:String): Call<TopRatedMovies>



}