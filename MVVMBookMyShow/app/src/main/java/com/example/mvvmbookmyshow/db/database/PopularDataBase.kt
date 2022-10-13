package com.example.mvvmbookmyshow.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvmbookmyshow.db.Converters
import com.example.mvvmbookmyshow.db.dao.PopularDao
import com.example.mvvmbookmyshow.models.PopularMovie.Result

@Database(
    entities = [Result::class],
    version = 1
)

@TypeConverters(
    Converters::class
)

abstract class PopularDataBase : RoomDatabase(){

    abstract fun getPopularMovieDao() : PopularDao
    companion object{
        @Volatile
        private var instance: PopularDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PopularDataBase::class.java,
                "popular_movie_db.db"
            ).build()

    }
}