package com.example.mvvmbookmyshow.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvmbookmyshow.db.Converters
import com.example.mvvmbookmyshow.db.dao.CastDao
import com.example.mvvmbookmyshow.models.CastCrew.Cast

@Database(
    entities = [Cast::class],
    version = 1

)
@TypeConverters(
    Converters::class
)


abstract class CastDataBase : RoomDatabase(){

    abstract fun getCastDao() : CastDao

    companion object{
        @Volatile
        private var instance: CastDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CastDataBase::class.java,
                "cast_db.db"
            ).build()

    }
}