package com.example.bookmyshowclone

import com.example.bookmyshowclone.Castdata.Crew

data class castData(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)