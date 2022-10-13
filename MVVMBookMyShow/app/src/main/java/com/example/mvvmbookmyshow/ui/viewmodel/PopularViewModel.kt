package com.example.mvvmbookmyshow.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmbookmyshow.models.PopularMovie.PopularMoviesApiResponse
import com.example.mvvmbookmyshow.repository.PopularRepository
import com.example.mvvmbookmyshow.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PopularViewModel(
    val popularRepository: PopularRepository
    ): ViewModel() {

    val popularMovie: MutableLiveData<Resource<PopularMoviesApiResponse>> = MutableLiveData()
    val popularMoviePage=1

    fun getPopularMovies() = viewModelScope.launch {
        popularMovie.postValue(Resource.Loading())
        val response = popularRepository.getMovies(popularMoviePage)
        popularMovie.postValue(handleMovieDetailsResponse(response))

    }

    private fun handleMovieDetailsResponse(response: Response<PopularMoviesApiResponse>): Resource<PopularMoviesApiResponse>? {
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}