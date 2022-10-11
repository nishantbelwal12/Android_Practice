package com.example.mvvmbookmyshow.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvmbookmyshow.repository.UpcomingRepository

class UpcomingViewModel(
    val upcomingRepository: UpcomingRepository
    ): ViewModel() {
}