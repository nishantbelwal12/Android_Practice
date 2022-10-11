package com.example.mvvmbookmyshow.ui.api

import com.example.mvvmbookmyshow.models.CastCrew.CastCrewApiResponse
import com.example.mvvmbookmyshow.models.MovieData.MovieDataApiResponse
import com.example.mvvmbookmyshow.models.PopularMovie.PopularMoviesApiResponse
import com.example.mvvmbookmyshow.models.UpcomingMovie.UpcomingMovieApiResponse
import com.example.mvvmbookmyshow.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("/movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movie_id:Int,
                                @Query("page") pageNumber: Int = 1,
                                @Query("api_key") key:String=API_KEY
                                ):

            Response<MovieDataApiResponse>

    @GET("/movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movie_id:Int,
                                @Query("page") pageNumber: Int = 1,
                                @Query("api_key") key:String=API_KEY
                                ):

            Response<CastCrewApiResponse>

    @GET("/movie/popular")
    suspend fun getPopularMovies(@Query("page") pageNumber: Int = 1,
                                 @Query("api_key") key:String=API_KEY
                                 ):

            Response<PopularMoviesApiResponse>

    @GET("/movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") pageNumber: Int = 1,
                                  @Query("api_key") key:String=API_KEY
                                  ):

            Response<UpcomingMovieApiResponse>



}