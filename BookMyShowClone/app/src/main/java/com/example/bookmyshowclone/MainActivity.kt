package com.example.bookmyshowclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.bookmyshowclone.RecyclerViews.LanguageAdapter
import com.example.bookmyshowclone.RecyclerViews.MoviesAdapter
import com.example.bookmyshowclone.RecyclerViews.PromotionsViewPager
import com.example.bookmyshowclone.Top_rated_Data.Result
import com.example.bookmyshowclone.Top_rated_Data.TopRatedMovies
import retrofit2.*

class MainActivity : AppCompatActivity() {

    private lateinit var newPromotionsViewPager: ViewPager2
    private lateinit var languageRecyclerView: RecyclerView
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieList:List<TopRatedMovies>
    private lateinit var comingSoonCard:CardView

    lateinit var promotionsList:ArrayList<Int>
    lateinit var languageList: ArrayList<String>
    lateinit var movieTitle:Array<Int>
    lateinit var movieName:Array<String>
    lateinit var movieReview:Array<String>
    lateinit var movieAdapter:MoviesAdapter

  //  val BASE_URL = "https://api.themoviedb.org"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newPromotionsViewPager = findViewById(R.id.vpMainPromotions)
        languageRecyclerView = findViewById(R.id.rvMainLanguages)
        movieRecyclerView = findViewById(R.id.rvMainMovies)
        comingSoonCard = findViewById(R.id.cvMainCommingSoon)

        languageRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        movieRecyclerView.layoutManager = GridLayoutManager(this,2)

        languageRecyclerView.setHasFixedSize(true)
        movieRecyclerView.setHasFixedSize(true)

        promotionsList = arrayListOf(
            R.drawable.promotion,
            R.drawable.promotion,
            R.drawable.promotion,
            R.drawable.promotion,
            R.drawable.promotion,
            R.drawable.promotion,
            R.drawable.promotion,
            R.drawable.promotion,
            R.drawable.promotion,

            )

        languageList = arrayListOf(
            "English",
            "Hindi",
            "Telugu",
            "Kannada",
            "Tamil",
            "Gujarati"
        )

        movieTitle = arrayOf(
            R.drawable.movie,
            R.drawable.movie,
            R.drawable.movie,
            R.drawable.movie,
            R.drawable.movie,
            R.drawable.movie,
            R.drawable.movie,
            R.drawable.movie,
            R.drawable.movie,
            R.drawable.movie,
        )

        movieName = arrayOf(
            "Movie 1",
            "Movie 2",
            "Movie 3",
            "Movie 4",
            "Movie 5",
            "Movie 6",
            "Movie 7",
            "Movie 8",
            "Movie 9",
            "Movie 10"
        )

        movieReview = arrayOf(

            "Movie 1 Review",
            "Movie 2 Review",
            "Movie 3 Review",
            "Movie 4 Review",
            "Movie 5 Review",
            "Movie 6 Review",
            "Movie 7 Review",
            "Movie 8 Review",
            "Movie 9 Review",
            "Movie 10 Review",
        )

  //      movieList = arrayListOf<Result>()
        //getUserData()
  //      getAPIData()


        val request = ServiceBuilder.buildService(ApiInterface::class.java)
        val call = request.getData(getString(R.string.api_key))

        call.enqueue(object : Callback<TopRatedMovies>
        {
            override fun onResponse(call: Call<TopRatedMovies>, response: Response<TopRatedMovies>) {
                if (response.isSuccessful){

//                    var movieList = arrayOf<TopRatedMovies>()
//                    for(i in response.body()!!.results.){
//                        movieList.add()
//                    }

                    movieRecyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@MainActivity,2)
                        movieAdapter = MoviesAdapter(response.body()!!.results)
                        movieRecyclerView.adapter = movieAdapter
                        println(response.body()!!.results[0])

                        movieAdapter.setOnItemClickListener(object :MoviesAdapter.onItemClickListener{
                            override fun onItemClick(position: Int) {

                                Toast.makeText(applicationContext, "Clicked on ", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@MainActivity,MoviePage::class.java)
                                intent.putExtra("position",position)
                                intent.putExtra("MovieId",response.body()!!.results[position].id)
                                println("Id = ${response.body()!!.results[position].id}")
                                startActivity(intent)
                            }

                        })
                    }
                }
            }
            override fun onFailure(call: Call<TopRatedMovies>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })


//        val movieAdapter = MoviesAdapter(movieList)
        val promotionAdapter = PromotionsViewPager(promotionsList)
        val languageAdapter = LanguageAdapter(languageList)

//        movieRecyclerView.adapter = movieAdapter
        newPromotionsViewPager.adapter = promotionAdapter
        languageRecyclerView.adapter = languageAdapter



        promotionAdapter.setOnItemClickListener(object :PromotionsViewPager.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(applicationContext, "clicked on promotions", Toast.LENGTH_SHORT).show()
            }

        })

        languageAdapter.setOnItemClickListener(object : LanguageAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(applicationContext, "Clicked on ${languageList[position]}", Toast.LENGTH_SHORT).show()
            }

        })

        comingSoonCard.setOnClickListener{

        }




    }
/*
    private fun getAPIData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData("adc974a309f4f43de62d31c0aa6a7578")

        retrofitData.enqueue(object : Callback<List<TopRatedMovies>?> {
            override fun onResponse(
                call: Call<List<TopRatedMovies>?>,
                response: Response<List<TopRatedMovies>?>
            ) {
                val responseBody = response.body()!!
                movieAdapter = MoviesAdapter(baseContext,responseBody)
                movieAdapter.notifyDataSetChanged()
                movieRecyclerView.adapter = movieAdapter
            }

            override fun onFailure(call: Call<List<TopRatedMovies>?>, t: Throwable) {
                Toast.makeText(applicationContext, "No Data Load ", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun getUserData() {

        for(i in movieTitle.indices){
            val movie = MovieItem(movieTitle[i],movieName[i],movieReview[i])
            movieList.add(movie)
        }
//        movieRecyclerView.adapter = MoviesAdapter(movieList)

    }


 */
}


