package com.example.mvvmbookmyshow.ui.viewmodelproviderfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmbookmyshow.repository.PopularRepository
import com.example.mvvmbookmyshow.ui.viewmodel.PopularViewModel

class PopularViewModelProviderFactory(
    val popularRepository: PopularRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PopularViewModel(popularRepository) as T
    }

}