package com.example.mybookmyshow.APIInterface

import com.example.mybookmyshow.DataClass.TopRated.TopRatedAPIData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Language {

    @GET("/3/movie/popular")
    fun getLanguageMovie(@Query("api_key") key: String, @Query("with_original_language") lang:String): Call<TopRatedAPIData>
}