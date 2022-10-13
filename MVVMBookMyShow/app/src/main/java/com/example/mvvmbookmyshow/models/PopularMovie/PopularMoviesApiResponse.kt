package com.example.mvvmbookmyshow.models.PopularMovie

import androidx.room.Dao
import androidx.room.Entity


//@Entity(
//    tableName = "PopularMovies"
//)
//@Dao
data class PopularMoviesApiResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)