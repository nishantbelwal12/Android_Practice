package com.example.mvvmbookmyshow.ui.viewmodelproviderfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmbookmyshow.repository.CastRepository
import com.example.mvvmbookmyshow.ui.viewmodel.CastViewModel

class CastViewModelProviderFactory(
    val castRepository: CastRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CastViewModel(castRepository) as T
    }

}