package com.example.mvvmbookmyshow.models.UpcomingMovie

import androidx.room.Dao
import androidx.room.Entity

//@Entity(
//    tableName = "UpcomingMovies"
//)
//@Dao
data class UpcomingMovieApiResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)