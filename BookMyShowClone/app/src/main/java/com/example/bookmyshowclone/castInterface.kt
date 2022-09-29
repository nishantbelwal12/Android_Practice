package com.example.bookmyshowclone

import com.example.bookmyshowclone.Castdata.CastData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface castInterface {

    @GET("/3/movie/{movie_id}/credits")
    fun getcredits(@Path("movie_id") movie_id:Int, @Query("api_key") key:String): Call<CastData>

}