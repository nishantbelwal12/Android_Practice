package com.example.mvvmbookmyshow.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvmbookmyshow.repository.CastRepository
import com.example.mvvmbookmyshow.repository.MovieRepository

class CastViewModel(
    val castRepository: CastRepository
    ): ViewModel() {
}