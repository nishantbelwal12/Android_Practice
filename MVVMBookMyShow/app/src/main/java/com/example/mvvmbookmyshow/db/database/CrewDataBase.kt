package com.example.mvvmbookmyshow.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvmbookmyshow.db.Converters
import com.example.mvvmbookmyshow.models.CastCrew.Crew

@Database(
    entities = [Crew::class],
    version = 1

)
@TypeConverters(
    Converters::class
)


abstract class CrewDataBase : RoomDatabase(){

    abstract fun getCrewDao() : Crew

    companion object{
        @Volatile
        private var instance: CrewDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CrewDataBase::class.java,
                "crew_db.db"
            ).build()

    }
}