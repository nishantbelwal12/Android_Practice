package com.example.mybookmyshow

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService

const val shortcut_1 = "Shortcut 1"
const val shortcut_2 = "Shortcut 2"

@RequiresApi(Build.VERSION_CODES.N_MR1)
object Shortcuts {
    fun setUp(context: Context){
        val shortcutManager = getSystemService<ShortcutManager>(context,ShortcutManager::class.java)

        val upcomingMovieintents = arrayOf(Intent(Intent.ACTION_VIEW,null,context,MainActivity::class.java),
        Intent(Intent.ACTION_VIEW,null,context,UpcomingMovies::class.java))

        val shortcutUpcomingMovies = ShortcutInfo.Builder(context, shortcut_1)
            .setShortLabel("Upcoming Movies")
            .setLongLabel("Find upcoming movies")
            .setIcon(Icon.createWithResource(context, R.drawable.ic_baseline_movie_creation_24))
            .setIntents(upcomingMovieintents)
            .build()

        val movieIntent = Intent(context,MoviePageActivity::class.java)
//        movieIntent.putExtra("MovieId",500n)
        movieIntent.action = Intent.ACTION_VIEW
        movieIntent.data = Uri.parse("500")
        movieIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK

        val moviePageintents = arrayOf(Intent(Intent.ACTION_VIEW,null,context,MainActivity::class.java),
            movieIntent)

        val shortcutMoviePage = ShortcutInfo.Builder(context, shortcut_2)
            .setShortLabel("Favourite Movie")
            .setLongLabel("Your Favouriite movie")
            .setIcon(Icon.createWithResource(context,R.drawable.ic_baseline_adjust_24))
            .setIntents(moviePageintents)
            .build()

        shortcutManager!!.dynamicShortcuts = listOf(shortcutUpcomingMovies,shortcutMoviePage)
    }
}