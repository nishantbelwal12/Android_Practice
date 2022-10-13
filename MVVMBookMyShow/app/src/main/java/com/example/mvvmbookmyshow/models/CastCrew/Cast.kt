package com.example.mvvmbookmyshow.models.CastCrew

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Cast"

)
data class Cast(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    @PrimaryKey
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)