package com.example.mvvmbookmyshow.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmbookmyshow.models.CastCrew.Cast


@Dao
interface CastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(cast: Cast):Long

    @Query("SELECT * FROM `Cast`")
    fun getAllMovies(): LiveData<List<Cast>>

    @Delete
    suspend fun deleteMovie(cast: Cast)
}