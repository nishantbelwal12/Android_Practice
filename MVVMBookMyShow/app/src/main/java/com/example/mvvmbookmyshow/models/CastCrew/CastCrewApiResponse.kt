package com.example.mvvmbookmyshow.models.CastCrew

data class CastCrewApiResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)