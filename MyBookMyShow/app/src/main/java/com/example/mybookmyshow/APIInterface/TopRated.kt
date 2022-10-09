package com.example.mybookmyshow.APIInterface

import com.example.mybookmyshow.DataClass.TopRated.TopRatedAPIData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRated {

    @GET("/3/movie/popular")
    fun getTopRated(@Query("api_key") key: String): Call<TopRatedAPIData>
}