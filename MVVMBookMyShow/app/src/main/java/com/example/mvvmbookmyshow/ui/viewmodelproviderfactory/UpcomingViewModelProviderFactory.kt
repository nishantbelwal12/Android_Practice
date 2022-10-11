package com.example.mvvmbookmyshow.ui.viewmodelproviderfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmbookmyshow.repository.UpcomingRepository
import com.example.mvvmbookmyshow.ui.viewmodel.UpcomingViewModel

class UpcomingViewModelProviderFactory(
    val upcomingRepository: UpcomingRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UpcomingViewModel(upcomingRepository) as T
    }

}