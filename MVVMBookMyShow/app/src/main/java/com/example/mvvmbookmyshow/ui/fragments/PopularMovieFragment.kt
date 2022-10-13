package com.example.mvvmbookmyshow.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvmbookmyshow.R
import com.example.mvvmbookmyshow.adapters.PopularAdapter
import com.example.mvvmbookmyshow.ui.MovieActivity
import com.example.mvvmbookmyshow.ui.viewmodel.PopularViewModel
import com.example.mvvmbookmyshow.util.Resource
import kotlinx.android.synthetic.main.fragment_popular_movie.*

class PopularMovieFragment:Fragment() {

    lateinit var popularViewModel: PopularViewModel
    lateinit var popularAdapter: PopularAdapter

    val TAG = "PopularMoviePageFragment"

    @SuppressLint("LongLogTag")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_movie, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        popularViewModel = (activity as MovieActivity).popularViewModel
        setupRecyclerView()
        popularViewModel.popularMovie.observe(viewLifecycleOwner, Observer { response->
            when(response){
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let { popularMovieApiResponse ->
                        popularAdapter.differ.submitList(popularMovieApiResponse.results)
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
        popularAdapter = PopularAdapter()
        rvPopularMovie.apply {
            adapter = popularAdapter
            layoutManager = GridLayoutManager(activity,2)

        }

    }
}