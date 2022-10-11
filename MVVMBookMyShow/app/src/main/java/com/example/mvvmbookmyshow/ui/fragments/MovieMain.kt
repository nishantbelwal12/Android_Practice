package com.example.mvvmbookmyshow.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvmbookmyshow.R
import com.example.mvvmbookmyshow.adapters.MovieAdapter
import com.example.mvvmbookmyshow.adapters.PopularAdapter
import com.example.mvvmbookmyshow.ui.MovieActivity
import com.example.mvvmbookmyshow.ui.viewmodel.MovieViewModel
import com.example.mvvmbookmyshow.ui.viewmodel.PopularViewModel
import com.example.mvvmbookmyshow.util.Resource
import kotlinx.android.synthetic.main.fragment_movie_main.*

class MovieMain:Fragment(R.layout.fragment_movie_main) {

    lateinit var viewModel: PopularViewModel
    lateinit var movieAdapter: PopularAdapter

    val TAG = "PopularMoviePageFragment"

    @SuppressLint("LongLogTag")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MovieActivity).viewModel
        setupRecyclerView()
        viewModel.popularMovie.observe(viewLifecycleOwner, Observer { response->
            when(response){
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let { movieDataApiResponse ->
                        movieAdapter.differ.submitList(movieDataApiResponse.results)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG,"An error occured: $message")

                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }

            }
        })
    }
    private fun hideProgressBar(){
        paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar(){
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        movieAdapter = PopularAdapter()
        rvMoviePage.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(activity,2)

        }

    }
}