package com.example.mybookmyshow

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybookmyshow.APIInterface.CastCrew
import com.example.mybookmyshow.APIInterface.Movie
import com.example.mybookmyshow.APIInterface.RelatedMovies
import com.example.mybookmyshow.DataClass.CastCrewData.CastCrewAPIData
import com.example.mybookmyshow.DataClass.MovieData.MovieAPIData
import com.example.mybookmyshow.DataClass.TopRated.TopRatedAPIData
import com.example.mybookmyshow.RecyclerAdapter.CastAdapter
import com.example.mybookmyshow.RecyclerAdapter.CrewAdapter
import com.example.mybookmyshow.RecyclerAdapter.SimilarMoviesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MoviePageActivity : AppCompatActivity() {

    private lateinit var similarRecyclerView: RecyclerView
    private lateinit var castRecyclerView: RecyclerView

    private lateinit var crewRecyclerView: RecyclerView

    lateinit var similarAdapter: SimilarMoviesAdapter
    lateinit var castAdapter: CastAdapter
    lateinit var crewAdapter: CrewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_page)

        val bundle: Bundle?= intent.extras

        val image: ImageView = findViewById(R.id.ivMoviePosterMoviePage)
        val releaseDate: TextView = findViewById(R.id.tvReleaseDateMoviePage)
        val about: TextView = findViewById(R.id.tvAboutMoviePage)
        val reviews: TextView = findViewById(R.id.tvLikesMoviePage)
        val duration: TextView = findViewById(R.id.tvDurationMoviePage)
//        rating.text = bundle?.getInt("position").toString()
        val movieId = bundle?.getInt("MovieId")


        val request = ServiceBuilder.buildService(Movie::class.java)
        val call = movieId?.let { request.getMovie(it,getString(R.string.api_key)) }

        println("Got Bundle $movieId")

        if (call != null) {
            call.enqueue(object : Callback<MovieAPIData>
            {

                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: Call<MovieAPIData>, response: Response<MovieAPIData>) {
                    if (response.isSuccessful){

                        //                    var movieList = arrayOf<TopRatedMovies>()
                        //                    for(i in response.body()!!.results.){
                        //                        movieList.add()
                        //                    }
                        val newResponse = ServiceBuilder.buildService(RelatedMovies::class.java)
                        val callNew = newResponse.getSimilar(movieId,getString(R.string.api_key))
                        val resp = response.body()!!
                        println("Inside if")
                        println(response.body())

                        val c = Calendar.getInstance()
                        val year = c.get(Calendar.DATE)

                        val current = LocalDateTime.now()

                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        val formatted = current.format(formatter)

                        if(resp.release_date.compareTo(formatted)>0){
                            releaseDate.text = "Releasing on ${resp.release_date}"
                        }
                        else{
                            releaseDate.text = "Released on ${resp.release_date}"
                        }


                        about.text = resp.overview
                        val original_line_count = about.lineCount
                        var display_full = false
                        about.setOnClickListener {
                            if(display_full){
                                about.maxLines=2
                            }
                            else{
                                about.maxLines=original_line_count
                            }
                            display_full = !display_full
                        }

                        reviews.text = resp.popularity.toInt().toString()
                        duration.text = "Duration: - ${resp.runtime.toString()} mins"

                        /*
                        movieRecyclerView.apply {
                            setHasFixedSize(true)
                            layoutManager = GridLayoutManager(this@MainActivity,2)
                            movieAdapter = MoviesAdapter(response.body()!!.results)
                            movieRecyclerView.adapter = movieAdapter
                            println(response.body()!!.results[0])

                            movieAdapter.setOnItemClickListener(object : MoviesAdapter.onItemClickListener{
                                override fun onItemClick(position: Int) {

                                    Toast.makeText(applicationContext, "Clicked on ", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this@MainActivity,MoviePage::class.java)
                                    intent.putExtra("position",position)
                                    intent.putExtra("MovieId",response.body()!!.results[position].id)
                                    startActivity(intent)
                                }

                            })
                        }*/
                        val imagePath = "http://image.tmdb.org/t/p/w500${resp.poster_path}"
                        Glide.with(baseContext).load(imagePath).dontAnimate().into(image)

                        val newResponseCast = ServiceBuilder.buildService(CastCrew::class.java)
                        val callNewCast = newResponseCast.getcredits(movieId,getString(R.string.api_key))

                        callNewCast.enqueue(object : Callback<CastCrewAPIData> {
                            override fun onResponse(
                                call: Call<CastCrewAPIData>,
                                response: Response<CastCrewAPIData>
                            ) {
                                castRecyclerView = findViewById(R.id.rvCastMoviePage)
                                castRecyclerView.layoutManager = LinearLayoutManager(this@MoviePageActivity,
                                    LinearLayoutManager.HORIZONTAL,false)
                                castRecyclerView.setHasFixedSize(true)
                                castAdapter = CastAdapter(response.body()!!.cast)
                                castRecyclerView.adapter = castAdapter

                                crewRecyclerView = findViewById(R.id.rvCrewMoviePage)
                                crewRecyclerView.layoutManager = LinearLayoutManager(this@MoviePageActivity,
                                    LinearLayoutManager.HORIZONTAL,false)
                                crewRecyclerView.setHasFixedSize(true)
                                crewAdapter = CrewAdapter(response.body()!!.crew)
                                crewRecyclerView.adapter = crewAdapter



                                castAdapter.setOnItemClickListener(object :CastAdapter.onItemClickListener{
                                    override fun onItemClick(position: Int) {

                                        Toast.makeText(this@MoviePageActivity, "Clicked on Cast", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this@MoviePageActivity,CastCrewDisplay::class.java)
                                        intent.putExtra("position",position)
                                        intent.putExtra("CastCrewId",response.body()!!.cast[position].id)
//                                        println("Id = ${response.body()!!.crew[position].id}")
                                        startActivity(intent)


                                    }

                                })

                                crewAdapter.setOnItemClickListener(object : CrewAdapter.onItemClickListener{
                                    override fun onItemClick(position: Int) {
                                        Toast.makeText(this@MoviePageActivity, "Clicked on Crew", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this@MoviePageActivity,CastCrewDisplay::class.java)
                                        intent.putExtra("position",position)
                                        intent.putExtra("CastCrewId",response.body()!!.crew[position].id)
//                                        println("Id = ${response.body()!!.cast[position].id}")
                                        startActivity(intent)
                                    }

                                })
                            }

                            override fun onFailure(call: Call<CastCrewAPIData>, t: Throwable) {
                                println("no cast found")
                            }

                        })






                        callNew.enqueue(object : Callback<TopRatedAPIData> {
                            override fun onResponse(
                                call: Call<TopRatedAPIData>,
                                response: Response<TopRatedAPIData>
                            ) {



                                similarRecyclerView = findViewById(R.id.rvYouMightLikeMoviePage)
                                similarRecyclerView.layoutManager = LinearLayoutManager(this@MoviePageActivity,
                                    LinearLayoutManager.HORIZONTAL,false)
                                similarRecyclerView.setHasFixedSize(true)
                                similarAdapter = SimilarMoviesAdapter(response.body()!!.results)
                                similarRecyclerView.adapter = similarAdapter



                                similarAdapter.setOnItemClickListener(object :SimilarMoviesAdapter.onItemClickListener{
                                    override fun onItemClick(position: Int) {

                                        Toast.makeText(this@MoviePageActivity, "Clicked on ", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this@MoviePageActivity,MoviePageActivity::class.java)
                                        intent.putExtra("position",position)
                                        intent.putExtra("MovieId",response.body()!!.results[position].id)
                                        println("Id = ${response.body()!!.results[position].id}")
                                        startActivity(intent)

                                    }

                                })
                            }

                            override fun onFailure(call: Call<TopRatedAPIData>, t: Throwable) {
                                TODO("Not yet implemented")
                            }

                        })
                    }
                }

                override fun onFailure(call: Call<MovieAPIData>, t: Throwable) {
                    println("Inside failure")
                    Toast.makeText(applicationContext, "not found", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}