package com.example.mybookmyshow.APIInterface

import com.example.mybookmyshow.DataClass.TopRated.TopRatedAPIData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchMovie {

    @GET("3/search/movie")
    fun getSearch(@Query("api_key") key:String,@Query("query") movie:String): Call<TopRatedAPIData>
}