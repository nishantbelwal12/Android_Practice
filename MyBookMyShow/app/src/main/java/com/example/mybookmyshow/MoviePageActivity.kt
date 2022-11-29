package com.example.mybookmyshow

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybookmyshow.APIInterface.CastCrew
import com.example.mybookmyshow.APIInterface.Movie
import com.example.mybookmyshow.APIInterface.RelatedMovies
import com.example.mybookmyshow.APIInterface.Trailer
import com.example.mybookmyshow.DataClass.CastCrewData.CastCrewAPIData
import com.example.mybookmyshow.DataClass.MovieData.MovieAPIData
import com.example.mybookmyshow.DataClass.TopRated.TopRatedAPIData
import com.example.mybookmyshow.DataClass.Trailer.TrailerAPIResponse
import com.example.mybookmyshow.RecyclerAdapter.CastAdapter
import com.example.mybookmyshow.RecyclerAdapter.CrewAdapter
import com.example.mybookmyshow.RecyclerAdapter.SimilarMoviesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MoviePageActivity : YouTubeBaseActivity() {

    private lateinit var similarRecyclerView: RecyclerView
    private lateinit var castRecyclerView: RecyclerView

    private lateinit var crewRecyclerView: RecyclerView
    private lateinit var youTubePlayer: YouTubePlayerView

    lateinit var similarAdapter: SimilarMoviesAdapter
    lateinit var castAdapter: CastAdapter
    lateinit var crewAdapter: CrewAdapter
    lateinit var youtubePlayerInit:YouTubePlayer.OnInitializedListener
    lateinit var progressBar: ProgressBar

    var defaultMovieId = 500

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_page)

        val bundle: Bundle?= intent.extras
        val releaseDate: TextView = findViewById(R.id.tvReleaseDateMoviePage)
        val about: TextView = findViewById(R.id.tvAboutMoviePage)
        val reviews: TextView = findViewById(R.id.tvLikesMoviePage)
        val duration: TextView = findViewById(R.id.tvDurationMoviePage)
        val movieName: TextView = findViewById(R.id.tvMovieNameMoviePage)
        val fabMoviePage: FloatingActionButton = findViewById(R.id.fabMoviePage)
        var movieId = bundle?.getInt("MovieId")
        progressBar = findViewById(R.id.progress_bar_movie_page)

        if(movieId==null){

            this.openFileInput(getString(R.string.filename)).bufferedReader().forEachLine { lines ->

                if(lines!=null){
                    movieId=lines.toInt()
                }
                else{
                    movieId=defaultMovieId
                }
            }
        }

        fabMoviePage.setOnClickListener {
            val fileContents = movieId
            File(getString(R.string.filename)).delete()
            this.openFileOutput(getString(R.string.filename), MODE_PRIVATE).use {
                it.write(fileContents.toString().toByteArray())
                println("\n\n\n\n writing movie $fileContents \n\n\n\n")
            }
        }

        val request = ServiceBuilder.buildService(Movie::class.java)
        val call = movieId?.let { request.getMovie(it,getString(R.string.api_key)) }

        if (call != null) {
            call.enqueue(object : Callback<MovieAPIData>
            {

                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: Call<MovieAPIData>, response: Response<MovieAPIData>) {
                    if (response.isSuccessful){

                        progressBar.visibility = View.GONE
                        val newResponse = ServiceBuilder.buildService(RelatedMovies::class.java)
                        val callNew = newResponse.getSimilar(movieId,getString(R.string.api_key))
                        val resp = response.body()!!
                        val current = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        val formatted = current.format(formatter)

                        if(resp.release_date > formatted){
                            releaseDate.text = "Releasing on ${resp.release_date}"
                        }
                        else{
                            releaseDate.text = "Released on ${resp.release_date}"
                        }

                        about.text = resp.overview
                        val totalLines = about.lineCount
                        about.maxLines = 2
                        var fullText = false

                        about.setOnClickListener(){
                            if(fullText){
                                about.maxLines = 2
                                fullText = false
                            }
                            else{
                                about.maxLines = totalLines
                                fullText = true
                            }
                        }
                        movieName.text = resp.original_title
                        reviews.text = resp.popularity.toInt().toString()
                        duration.text = "Duration: - ${resp.runtime.toString()} mins"

                        val newResponseTrailer = ServiceBuilder.buildService(Trailer::class.java)
                        val callNewTrailer = newResponseTrailer.getTrailer(movieId,getString(R.string.api_key))

                        callNewTrailer.enqueue(object : Callback<TrailerAPIResponse> {
                            override fun onResponse(
                                call: Call<TrailerAPIResponse>,
                                response: Response<TrailerAPIResponse>
                            ) {

                                println("\n\n\n\n\n\n response of video osdnasfas ${response.body()!!.results}\n\n\n\n")
                                youTubePlayer = findViewById(R.id.vvMovieVideoMoviePage)

                                youtubePlayerInit = object: YouTubePlayer.OnInitializedListener{
                                    override fun onInitializationSuccess(
                                        p0: YouTubePlayer.Provider?,
                                        p1: YouTubePlayer?,
                                        p2: Boolean
                                    ) {
                                        if(response.body()!!.results.isNotEmpty()){

                                            if(response.body()!!.results[0].key.isNotEmpty()){
                                                p1?.cueVideo(response.body()!!.results[0].key)
//                                                println("\n\n\n\n\n Video response ${response.body()!!.results[0].key}\n\n\n")
                                            }
                                            else{
                                                p1?.loadVideo(R.string.samplevideo.toString())
                                            }

                                        }
                                        else{
                                            p1?.loadVideo(R.string.samplevideo.toString())
                                        }

                                    }

                                    override fun onInitializationFailure(
                                        p0: YouTubePlayer.Provider?,
                                        p1: YouTubeInitializationResult?
                                    ) {
                                        Toast.makeText(applicationContext, "Failed $p1", Toast.LENGTH_SHORT).show()
                                    }

                                }
                                youTubePlayer.initialize("AIzaSyDSuJrD8RZtgm0r__8QwloAQekROA9PZMM",youtubePlayerInit)

                            }

                            override fun onFailure(call: Call<TrailerAPIResponse>, t: Throwable) {
                                TODO("Not yet implemented")
                            }
                        })

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

//                                        Toast.makeText(this@MoviePageActivity, "Clicked on Cast", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this@MoviePageActivity,CastCrewDisplay::class.java)
                                        intent.putExtra("position",position)
                                        intent.putExtra("CastCrewId",response.body()!!.cast[position].id)
//                                        println("Id = ${response.body()!!.crew[position].id}")
                                        startActivity(intent)


                                    }

                                })

                                crewAdapter.setOnItemClickListener(object : CrewAdapter.onItemClickListener{
                                    override fun onItemClick(position: Int) {
//                                        Toast.makeText(this@MoviePageActivity, "Clicked on Crew", Toast.LENGTH_SHORT).show()
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

//                                        Toast.makeText(this@MoviePageActivity, "Clicked on ", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this@MoviePageActivity,MoviePageActivity::class.java)
                                        intent.putExtra("position",position)
                                        intent.putExtra("MovieId",response.body()!!.results[position].id)
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
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
                    progressBar.visibility = View.VISIBLE
                    Toast.makeText(applicationContext, "Movie Details not found", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}