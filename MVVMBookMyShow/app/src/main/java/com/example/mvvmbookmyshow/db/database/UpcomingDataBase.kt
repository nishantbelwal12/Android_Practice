package com.example.mvvmbookmyshow.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvmbookmyshow.db.Converters
import com.example.mvvmbookmyshow.models.UpcomingMovie.Result

@Database(
    entities = [Result::class],
    version = 1

)
@TypeConverters(
    Converters::class
)


abstract class UpcomingDataBase : RoomDatabase(){

    abstract fun getUpcomingMovieDao() : Result

    companion object{
        @Volatile
        private var instance: UpcomingDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UpcomingDataBase::class.java,
                "upcoming_movie_db.db"
            ).build()

    }
}