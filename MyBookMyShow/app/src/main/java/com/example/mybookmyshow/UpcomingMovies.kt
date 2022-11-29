package com.example.mybookmyshow

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybookmyshow.APIInterface.Upcoming
import com.example.mybookmyshow.DataClass.UpcomingMovies.UpcomingMovieAPIData
import com.example.mybookmyshow.RecyclerAdapter.UpcomingMovieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingMovies : AppCompatActivity() {

    private lateinit var upcomingRecycler: RecyclerView
    lateinit var upcomingMovieAdapter:UpcomingMovieAdapter
    lateinit var progressBar: ProgressBar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming_movies)

        val request = ServiceBuilder.buildService(Upcoming::class.java)
        val call = request.getUpcoming(getString(R.string.api_key))
        progressBar = findViewById(R.id.progress_bar_upcoming_movie)

        call.enqueue(object : Callback<UpcomingMovieAPIData>
        {
            override fun onResponse(call: Call<UpcomingMovieAPIData>, response: Response<UpcomingMovieAPIData>) {
                if (response.isSuccessful){

                    progressBar.visibility = View.GONE
                    println("Inside upcoming movie")
                    println(response.body()!!.results)
                    upcomingRecycler = findViewById(R.id.rvUpcomingMoviePage)

                    upcomingRecycler.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@UpcomingMovies,2)
                        upcomingMovieAdapter = UpcomingMovieAdapter(response.body()!!.results)
                        upcomingRecycler.adapter = upcomingMovieAdapter
                        println(response.body()!!.results[0])

                        upcomingMovieAdapter.setOnItemClickListener(object : UpcomingMovieAdapter.onItemClickListener{
                            override fun onItemClick(position: Int) {

                                println("Clicked on Upcoming movie page")
//                                Toast.makeText(applicationContext, "Clicked on ", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@UpcomingMovies,MoviePageActivity::class.java)
                                intent.putExtra("position",position)
                                intent.putExtra("MovieId",response.body()!!.results[position].id)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                println("Id = ${response.body()!!.results[position].id}")
                                startActivity(intent)
                            }

                        })
                    }
                }
            }
            override fun onFailure(call: Call<UpcomingMovieAPIData>, t: Throwable) {
                progressBar.visibility = View.VISIBLE
                Toast.makeText(this@UpcomingMovies, "Internet not available: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })


    }
}