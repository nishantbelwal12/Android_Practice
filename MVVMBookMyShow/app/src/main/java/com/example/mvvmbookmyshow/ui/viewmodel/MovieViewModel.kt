package com.example.mvvmbookmyshow.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmbookmyshow.models.MovieData.MovieDataApiResponse
import com.example.mvvmbookmyshow.repository.MovieRepository
import com.example.mvvmbookmyshow.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(
    val movieRepository: MovieRepository
    ): ViewModel() {

    val movie:MutableLiveData<Resource<MovieDataApiResponse>> = MutableLiveData()
    val moviePage=1
    fun getMovieDetails(movieId:Int) = viewModelScope.launch {
        movie.postValue(Resource.Loading())
        val response = movieRepository.getMovieDetails(movieId,moviePage)
        movie.postValue(handleMovieDetailsResponse(response))

    }

    private fun handleMovieDetailsResponse(response: Response<MovieDataApiResponse>):Resource<MovieDataApiResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}