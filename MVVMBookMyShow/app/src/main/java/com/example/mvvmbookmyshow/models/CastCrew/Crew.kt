package com.example.mvvmbookmyshow.models.CastCrew

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Crew"

)
data class Crew(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int,
    @PrimaryKey
    val id: Int,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)