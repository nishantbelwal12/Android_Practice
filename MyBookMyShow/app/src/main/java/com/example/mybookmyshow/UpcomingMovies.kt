package com.example.mybookmyshow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming_movies)

        upcomingRecycler = findViewById(R.id.rvUpcomingMoviePage)

        val request = ServiceBuilder.buildService(Upcoming::class.java)
        val call = request.getUpcoming(getString(R.string.api_key))

        call.enqueue(object : Callback<UpcomingMovieAPIData>
        {
            override fun onResponse(call: Call<UpcomingMovieAPIData>, response: Response<UpcomingMovieAPIData>) {
                if (response.isSuccessful){

//                    var movieList = arrayOf<TopRatedMovies>()
//                    for(i in response.body()!!.results.){
//                        movieList.add()
//                    }
                    println("Inside upcoming movie")
                    println(response.body()!!.results)

                    upcomingRecycler.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@UpcomingMovies,2)
                        upcomingMovieAdapter = UpcomingMovieAdapter(response.body()!!.results)
                        upcomingRecycler.adapter = upcomingMovieAdapter
                        println(response.body()!!.results[0])

                        upcomingMovieAdapter.setOnItemClickListener(object : UpcomingMovieAdapter.onItemClickListener{
                            override fun onItemClick(position: Int) {

                                println("Clicked on Upcoming movie page")
                                Toast.makeText(applicationContext, "Clicked on ", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@UpcomingMovies,MoviePageActivity::class.java)
                                intent.putExtra("position",position)
                                intent.putExtra("MovieId",response.body()!!.results[position].id)
                                println("Id = ${response.body()!!.results[position].id}")
                                startActivity(intent)
                            }

                        })
                    }
                }
            }
            override fun onFailure(call: Call<UpcomingMovieAPIData>, t: Throwable) {
                Toast.makeText(this@UpcomingMovies, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })


    }
}