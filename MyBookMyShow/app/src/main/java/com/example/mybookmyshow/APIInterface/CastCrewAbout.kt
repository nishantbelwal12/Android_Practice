package com.example.mybookmyshow.APIInterface

import com.example.mybookmyshow.DataClass.CastCrewAbout.CastCrewAboutAPIData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CastCrewAbout {

    @GET("/3/person/{person_id}")
    fun getAbout(@Path("person_id") movie_id:Int, @Query("api_key") key:String): Call<CastCrewAboutAPIData>
}