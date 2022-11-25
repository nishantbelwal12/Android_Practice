package com.example.mybookmyshow.APIInterface

import com.example.mybookmyshow.DataClass.Trailer.TrailerAPIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Trailer {

    @GET("/3/movie/{movie_id}/videos")
    fun getTrailer(@Path("movie_id") movie_id:Int, @Query("api_key") key:String): Call<TrailerAPIResponse>

}