package com.example.mvvmbookmyshow.models.PopularMovie



data class PopularMoviesApiResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)