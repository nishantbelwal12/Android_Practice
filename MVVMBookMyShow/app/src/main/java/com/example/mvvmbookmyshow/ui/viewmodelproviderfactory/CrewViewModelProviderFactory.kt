package com.example.mvvmbookmyshow.ui.viewmodelproviderfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmbookmyshow.repository.CrewRepository
import com.example.mvvmbookmyshow.ui.viewmodel.CrewViewModel

class CrewViewModelProviderFactory(
    val crewRepository: CrewRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CrewViewModel(crewRepository) as T

    }

}