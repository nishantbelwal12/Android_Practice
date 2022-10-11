package com.example.mvvmbookmyshow.repository

import com.example.mvvmbookmyshow.api.RetrofitInstance
import com.example.mvvmbookmyshow.db.database.MovieDataBase

class MovieRepository(
    val db: MovieDataBase
) {
    suspend fun getMovieDetails(movieId:Int,pageNumber:Int) = RetrofitInstance.api.getMovieDetails(movieId,pageNumber)
}