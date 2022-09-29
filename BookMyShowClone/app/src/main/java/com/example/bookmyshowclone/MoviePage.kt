package com.example.bookmyshowclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookmyshowclone.Castdata.CastData
import com.example.bookmyshowclone.Movie_Data.MovieDataAll
import com.example.bookmyshowclone.RecyclerViews.CastAdapter
import com.example.bookmyshowclone.RecyclerViews.MoviesAdapter
import com.example.bookmyshowclone.Top_rated_Data.TopRatedMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePage : AppCompatActivity() {


    private lateinit var similarRecyclerView: RecyclerView
    private lateinit var castRecyclerView: RecyclerView

    lateinit var similarAdapter: MoviesAdapter
    lateinit var castAdapter: CastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_page)

        val bundle: Bundle?= intent.extras

        val image: ImageView = findViewById(R.id.ivMoviePage)
        val rating:TextView = findViewById(R.id.tvMovieReview)
//        rating.text = bundle?.getInt("position").toString()
        val movieId = bundle?.getInt("MovieId")


        val request = ServiceBuilder.buildService(MovieInterface::class.java)
        val call = movieId?.let { request.getMovie(it,getString(R.string.api_key)) }

        println("Got Bundle $movieId")

        if (call != null) {
            call.enqueue(object : Callback<MovieDataAll>
            {

                override fun onResponse(call: Call<MovieDataAll>, response: Response<MovieDataAll>) {
                    if (response.isSuccessful){

    //                    var movieList = arrayOf<TopRatedMovies>()
    //                    for(i in response.body()!!.results.){
    //                        movieList.add()
    //                    }
                        val newResponse = ServiceBuilder.buildService(RelatedMovieInterface::class.java)
                        val callNew = newResponse.getSimilar(movieId,getString(R.string.api_key))
                        val resp = response.body()!!
                        println("Inside if")
                        println(response.body())

                        rating.text = resp.original_title
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

                        val newResponseCast = ServiceBuilder.buildService(castInterface::class.java)
                        val callNewCast = newResponseCast.getcredits(movieId,getString(R.string.api_key))

                        callNewCast.enqueue(object : Callback<CastData>{
                            override fun onResponse(
                                call: Call<CastData>,
                                response: Response<CastData>
                            ) {
                                castRecyclerView = findViewById(R.id.rvMovieCast)
                                castRecyclerView.layoutManager = LinearLayoutManager(this@MoviePage,LinearLayoutManager.HORIZONTAL,false)
                                castRecyclerView.setHasFixedSize(true)
                                castAdapter = CastAdapter(response.body()!!.cast)
                                castRecyclerView.adapter = castAdapter

                                castAdapter.setOnItemClickListener(object :CastAdapter.onItemClickListener{
                                    override fun onItemClick(position: Int) {

                                        Toast.makeText(this@MoviePage, "Clicked on ", Toast.LENGTH_SHORT).show()

                                    }

                                })
                            }

                            override fun onFailure(call: Call<CastData>, t: Throwable) {
                                println("no cast found")
                            }

                        })








                        callNew.enqueue(object : Callback<TopRatedMovies>{
                            override fun onResponse(
                                call: Call<TopRatedMovies>,
                                response: Response<TopRatedMovies>
                            ) {



                                similarRecyclerView = findViewById(R.id.rvMovieYouMightLike)
                                similarRecyclerView.layoutManager = LinearLayoutManager(this@MoviePage,LinearLayoutManager.HORIZONTAL,false)
                                similarRecyclerView.setHasFixedSize(true)
                                similarAdapter = MoviesAdapter(response.body()!!.results)
                                similarRecyclerView.adapter = similarAdapter



                                similarAdapter.setOnItemClickListener(object :MoviesAdapter.onItemClickListener{
                                    override fun onItemClick(position: Int) {

                                        Toast.makeText(this@MoviePage, "Clicked on ", Toast.LENGTH_SHORT).show()

                                    }

                                })
                            }

                            override fun onFailure(call: Call<TopRatedMovies>, t: Throwable) {
                                TODO("Not yet implemented")
                            }

                        })
                    }
                }

                override fun onFailure(call: Call<MovieDataAll>, t: Throwable) {
                    println("Inside failure")
                    Toast.makeText(applicationContext, "not found", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}