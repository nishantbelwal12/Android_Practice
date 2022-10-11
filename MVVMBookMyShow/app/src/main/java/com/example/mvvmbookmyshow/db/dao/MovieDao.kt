package com.example.mvvmbookmyshow.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmbookmyshow.models.MovieData.MovieDataApiResponse


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie:MovieDataApiResponse):Long

    @Query("SELECT * FROM Movies")
    fun getAllMovies(): LiveData<List<MovieDataApiResponse>>

    @Delete
    suspend fun deleteMovie(movie: MovieDataApiResponse)
}