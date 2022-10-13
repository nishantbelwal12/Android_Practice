package com.example.mvvmbookmyshow.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmbookmyshow.models.PopularMovie.Result


@Dao
interface PopularDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(popularMovie:Result):Long

    @Query("SELECT * FROM PopularMovies")
    fun getAllMovies(): LiveData<List<Result>>

    @Delete
    suspend fun deleteMovie(popularMovie: Result)
}