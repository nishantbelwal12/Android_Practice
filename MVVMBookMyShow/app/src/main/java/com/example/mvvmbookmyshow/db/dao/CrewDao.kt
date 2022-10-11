package com.example.mvvmbookmyshow.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmbookmyshow.models.CastCrew.Crew
import com.example.mvvmbookmyshow.models.MovieData.MovieDataApiResponse


@Dao
interface CrewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(crew:Crew):Long

    @Query("SELECT * FROM Crew")
    fun getAllMovies(): LiveData<List<Crew>>

    @Delete
    suspend fun deleteMovie(crew: Crew)
}