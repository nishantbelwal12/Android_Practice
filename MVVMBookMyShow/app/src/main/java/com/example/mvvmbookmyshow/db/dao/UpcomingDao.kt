package com.example.mvvmbookmyshow.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmbookmyshow.models.UpcomingMovie.Result


@Dao
interface UpcomingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(upcomingMovie:Result):Long

    @Query("SELECT * FROM UpcomingMovies")
    fun getAllMovies(): LiveData<List<Result>>

    @Delete
    suspend fun deleteMovie(upcomingMovie: Result)
}