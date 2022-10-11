package com.example.mvvmbookmyshow.ui.viewmodelproviderfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmbookmyshow.repository.MovieRepository
import com.example.mvvmbookmyshow.ui.viewmodel.MovieViewModel

class MovieViewModelProviderFactory(
    val movieRepository: MovieRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(movieRepository) as T
    }

}