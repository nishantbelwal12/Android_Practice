package com.example.bookmyshowclone

import com.example.bookmyshowclone.Top_rated_Data.TopRatedMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RelatedMovieInterface {

    @GET("/3/movie/{movie_id}/similar")
    fun getSimilar(@Path("movie_id") movieId:Int, @Query("api_key") key: String): Call<TopRatedMovies>
}