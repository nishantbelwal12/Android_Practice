package com.example.mvvmbookmyshow.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mvvmbookmyshow.R
import com.example.mvvmbookmyshow.db.database.MovieDataBase
import com.example.mvvmbookmyshow.db.database.PopularDataBase
import com.example.mvvmbookmyshow.repository.MovieRepository
import com.example.mvvmbookmyshow.repository.PopularRepository
import com.example.mvvmbookmyshow.ui.viewmodel.PopularViewModel
import com.example.mvvmbookmyshow.ui.viewmodelproviderfactory.MovieViewModelProviderFactory
import com.example.mvvmbookmyshow.ui.viewmodelproviderfactory.PopularViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity() {

    lateinit var popularViewModel: PopularViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val popularRepository = PopularRepository(PopularDataBase(this))
        val popularModelProviderFactory = PopularViewModelProviderFactory(popularRepository)
        popularViewModel = ViewModelProvider(this,popularModelProviderFactory).get(PopularViewModel::class.java)
        bottomNavigationView.setupWithNavController(movieNavHostFragment.findNavController())
    }
}