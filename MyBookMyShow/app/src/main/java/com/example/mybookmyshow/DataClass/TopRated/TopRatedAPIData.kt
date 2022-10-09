package com.example.mybookmyshow.DataClass.TopRated

data class TopRatedAPIData(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)