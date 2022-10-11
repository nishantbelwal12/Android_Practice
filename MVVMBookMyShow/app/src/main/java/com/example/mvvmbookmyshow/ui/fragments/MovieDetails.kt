package com.example.mvvmbookmyshow.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mvvmbookmyshow.R
import com.example.mvvmbookmyshow.adapters.MovieAdapter
import com.example.mvvmbookmyshow.ui.MovieActivity
import com.example.mvvmbookmyshow.ui.viewmodel.MovieViewModel

class MovieDetails:Fragment(R.layout.fragment_movie_details) {

    lateinit var viewModel: MovieViewModel
    lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MovieActivity).viewModel
    }

    private fun setupRecyclerView(){
        movieAdapter = MovieAdapter()

    }
}