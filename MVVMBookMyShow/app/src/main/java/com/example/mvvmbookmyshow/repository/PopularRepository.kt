package com.example.mvvmbookmyshow.repository

import com.example.mvvmbookmyshow.api.RetrofitInstance
import com.example.mvvmbookmyshow.db.database.PopularDataBase

class PopularRepository(
    val db: PopularDataBase
) {
    suspend fun getMovies(pageNumber:Int) = RetrofitInstance.api.getPopularMovies(pageNumber)
}