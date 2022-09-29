package com.example.bookmyshowclone.Top_rated_Data


data class TopRatedMovies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)