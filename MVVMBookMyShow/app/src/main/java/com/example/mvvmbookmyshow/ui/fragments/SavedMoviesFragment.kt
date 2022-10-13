package com.example.mvvmbookmyshow.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mvvmbookmyshow.R
import com.example.mvvmbookmyshow.ui.MovieActivity
import com.example.mvvmbookmyshow.ui.viewmodel.MovieViewModel

class SavedMoviesFragment:Fragment(R.layout.fragment_saved_movies) {

//    lateinit var viewModel: MovieViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = (activity as MovieActivity).viewModel
    }
}