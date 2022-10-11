package com.example.mvvmbookmyshow.models.UpcomingMovie



data class UpcomingMovieApiResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)