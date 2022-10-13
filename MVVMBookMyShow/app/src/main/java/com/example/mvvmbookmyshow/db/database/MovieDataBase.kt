package com.example.mvvmbookmyshow.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvmbookmyshow.db.Converters
import com.example.mvvmbookmyshow.db.dao.MovieDao
import com.example.mvvmbookmyshow.models.MovieData.MovieDataApiResponse

@Database(
    entities = [MovieDataApiResponse::class],
    version = 1

)
@TypeConverters(
    Converters::class
)


abstract class MovieDataBase : RoomDatabase(){

    abstract fun getMovieDao() : MovieDao

    companion object{
        @Volatile
        private var instance: MovieDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDataBase::class.java,
                "movie_db.db"
            ).build()

    }
}