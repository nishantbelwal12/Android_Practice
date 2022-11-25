package com.example.mybookmyshow

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.mybookmyshow.APIInterface.Language
import com.example.mybookmyshow.APIInterface.SearchMovie
import com.example.mybookmyshow.APIInterface.TopRated
import com.example.mybookmyshow.DataClass.TopRated.TopRatedAPIData
import com.example.mybookmyshow.RecyclerAdapter.LanguageAdapter
import com.example.mybookmyshow.RecyclerAdapter.MoviesMainAdapter
import com.example.mybookmyshow.RecyclerAdapter.PromotionsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var newPromotionsViewPager: ViewPager2
    private lateinit var languageRecyclerView: RecyclerView
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var comingSoonCard: CardView

    lateinit var promotionsList:ArrayList<Int>
    lateinit var languageList: ArrayList<String>
    lateinit var languageCodeList: ArrayList<String>
    lateinit var movieAdapter:MoviesMainAdapter

    //  val BASE_URL = "https://api.themoviedb.org"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(Build.VERSION.SDK_INT>=25){
            Shortcuts.setUp(applicationContext)
        }

        newPromotionsViewPager = findViewById(R.id.vpMainActivity)
        languageRecyclerView = findViewById(R.id.rvLanguageMainActivity)
        movieRecyclerView = findViewById(R.id.rvMovieMainActivity)
        comingSoonCard = findViewById(R.id.cvComingSoonMainActivity)

        languageRecyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
        movieRecyclerView.layoutManager = GridLayoutManager(this,2)

        languageRecyclerView.setHasFixedSize(true)
        movieRecyclerView.setHasFixedSize(true)

        promotionsList = arrayListOf(
            R.drawable.promotion1,
            R.drawable.promotion2,
            R.drawable.promotion3,
            R.drawable.promotion4,
            R.drawable.promotion5,
            R.drawable.promotion6,
            R.drawable.promotion7,
            R.drawable.promotion8,
            R.drawable.promotion9

            )

        languageList = arrayListOf(
            "English",
            "Hindi",
            "Telugu",
            "Italian",
            "Latin",
            "Polish",
            "Danish",
            "German",
            "Spanish",
            "Russian",
            "Swedish"

        )
        languageCodeList = arrayListOf(
            "en",
            "hi",
            "te",
            "it",
            "la",
            "pl",
            "da",
            "de",
            "es",
            "ru",
            "sv"

        )
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = current.format(formatter)

        println("Hello \n\n\n\n $formatted \n\n\n\n")



        val request = ServiceBuilder.buildService(TopRated::class.java)
        val call = request.getTopRated(getString(R.string.api_key))

        call.enqueue(object : Callback<TopRatedAPIData>
        {
            override fun onResponse(call: Call<TopRatedAPIData>, response: Response<TopRatedAPIData>) {
                if (response.isSuccessful){

//                    var movieList = arrayOf<TopRatedMovies>()
//                    for(i in response.body()!!.results.){
//                        movieList.add()
//                    }

                    movieRecyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@MainActivity,2)
                        movieAdapter = MoviesMainAdapter(response.body()!!.results)
                        movieRecyclerView.adapter = movieAdapter
//                        println(response.body()!!.results[0])

                        movieAdapter.setOnItemClickListener(object :MoviesMainAdapter.onItemClickListener{
                            override fun onItemClick(position: Int) {

                                Toast.makeText(applicationContext, "Clicked on ", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@MainActivity,MoviePageActivity::class.java)
                                intent.putExtra("position",position)
                                intent.putExtra("MovieId",response.body()!!.results[position].id)
                                println("Id = ${response.body()!!.results[position].id}")
                                startActivity(intent)
                            }

                        })
                    }
                }
            }
            override fun onFailure(call: Call<TopRatedAPIData>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })


//        val movieAdapter = MoviesAdapter(movieList)
        val promotionAdapter = PromotionsAdapter(promotionsList)
        val languageAdapter = LanguageAdapter(languageList)

//        movieRecyclerView.adapter = movieAdapter
        newPromotionsViewPager.adapter = promotionAdapter
        languageRecyclerView.adapter = languageAdapter



        promotionAdapter.setOnItemClickListener(object :PromotionsAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {


                Toast.makeText(applicationContext, "clicked on promotions", Toast.LENGTH_SHORT).show()

            }

        })

        languageAdapter.setOnItemClickListener(object : LanguageAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {


                val request = ServiceBuilder.buildService(Language::class.java)
                val call = request.getLanguageMovie(getString(R.string.api_key),languageCodeList[position])

                call.enqueue(object : Callback<TopRatedAPIData>
                {
                    override fun onResponse(call: Call<TopRatedAPIData>, response: Response<TopRatedAPIData>) {
                        if (response.isSuccessful){

//                    var movieList = arrayOf<TopRatedMovies>()
//                    for(i in response.body()!!.results.){
//                        movieList.add()
//                    }

                            Toast.makeText(applicationContext, "Movies to display- ${response.body()!!.results.size}", Toast.LENGTH_SHORT).show()
//                            println("Length of response - ${response.body()!!.results.size}")
                            movieRecyclerView.apply {
                                setHasFixedSize(true)
                                layoutManager = GridLayoutManager(this@MainActivity,2)
                                movieAdapter = MoviesMainAdapter(response.body()!!.results)
                                movieRecyclerView.adapter = movieAdapter
//                                println(response.body()!!.results[0])

                                movieAdapter.setOnItemClickListener(object :MoviesMainAdapter.onItemClickListener{
                                    override fun onItemClick(position: Int) {

                                        Toast.makeText(applicationContext, "Clicked on ", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this@MainActivity,MoviePageActivity::class.java)
                                        intent.putExtra("position",position)
                                        intent.putExtra("MovieId",response.body()!!.results[position].id)
                                        intent.putExtra("language",response.body()!!.results[position].original_language)
                                        println("Id = ${response.body()!!.results[position].id}")
                                        startActivity(intent)
                                    }

                                })
                            }
                        }
                    }
                    override fun onFailure(call: Call<TopRatedAPIData>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })

            }

        })

        comingSoonCard.setOnClickListener{
            val intentComingSoon = Intent(this@MainActivity,UpcomingMovies::class.java)
            startActivity(intentComingSoon)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_item,menu)
        val item = menu?.findItem(R.id.search_action)
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                movieRecyclerView = findViewById(R.id.rvMovieMainActivity)
                movieRecyclerView.layoutManager = GridLayoutManager(this@MainActivity,2)


                val searchText = p0!!.toLowerCase(Locale.getDefault())
                if(searchText.isNotEmpty()){


                    val request = ServiceBuilder.buildService(SearchMovie::class.java)
                    val call = request.getSearch(getString(R.string.api_key),searchText)

                    call.enqueue(object : Callback<TopRatedAPIData>
                    {
                        override fun onResponse(call: Call<TopRatedAPIData>, response: Response<TopRatedAPIData>) {
                            if (response.isSuccessful){

//                    var movieList = arrayOf<TopRatedMovies>()
//                    for(i in response.body()!!.results.){
//                        movieList.add()
//                    }

                                movieRecyclerView.apply {
                                    setHasFixedSize(true)
                                    layoutManager = GridLayoutManager(this@MainActivity,2)
                                    movieAdapter = MoviesMainAdapter(response.body()!!.results)
                                    movieRecyclerView.adapter = movieAdapter
//                                    println(response.body()!!.results[0])

                                    movieAdapter.setOnItemClickListener(object :MoviesMainAdapter.onItemClickListener{
                                        override fun onItemClick(position: Int) {

                                            Toast.makeText(applicationContext, "Clicked on ", Toast.LENGTH_SHORT).show()
                                            val intent = Intent(this@MainActivity,MoviePageActivity::class.java)
                                            intent.putExtra("position",position)
                                            intent.putExtra("MovieId",response.body()!!.results[position].id)
                                            println("Id = ${response.body()!!.results[position].id}")
                                            startActivity(intent)
                                        }

                                    })
                                }
                            }
                        }
                        override fun onFailure(call: Call<TopRatedAPIData>, t: Throwable) {
                            Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })



                }
                else{


                    val request = ServiceBuilder.buildService(TopRated::class.java)
                    val call = request.getTopRated(getString(R.string.api_key))

                    call.enqueue(object : Callback<TopRatedAPIData>
                    {
                        override fun onResponse(call: Call<TopRatedAPIData>, response: Response<TopRatedAPIData>) {
                            if (response.isSuccessful){

//                    var movieList = arrayOf<TopRatedMovies>()
//                    for(i in response.body()!!.results.){
//                        movieList.add()
//                    }

                                movieRecyclerView.apply {
                                    setHasFixedSize(true)
                                    layoutManager = GridLayoutManager(this@MainActivity,2)
                                    movieAdapter = MoviesMainAdapter(response.body()!!.results)
                                    movieRecyclerView.adapter = movieAdapter
//                                    println(response.body()!!.results[0])

                                    movieAdapter.setOnItemClickListener(object :MoviesMainAdapter.onItemClickListener{
                                        override fun onItemClick(position: Int) {

                                            Toast.makeText(applicationContext, "Clicked on ", Toast.LENGTH_SHORT).show()
                                            val intent = Intent(this@MainActivity,MoviePageActivity::class.java)
                                            intent.putExtra("position",position)
                                            intent.putExtra("MovieId",response.body()!!.results[position].id)
                                            println("Id = ${response.body()!!.results[position].id}")
                                            startActivity(intent)
                                        }

                                    })
                                }
                            }
                        }
                        override fun onFailure(call: Call<TopRatedAPIData>, t: Throwable) {
                            Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })



                }
                return false
            }



            override fun onQueryTextChange(p0: String?): Boolean {

                movieRecyclerView = findViewById(R.id.rvMovieMainActivity)
                movieRecyclerView.layoutManager = GridLayoutManager(this@MainActivity,2)


                val searchText = p0!!.toLowerCase(Locale.getDefault())
                if(searchText.isNotEmpty()){


                    val request = ServiceBuilder.buildService(SearchMovie::class.java)
                    val call = request.getSearch(getString(R.string.api_key),searchText)

                    call.enqueue(object : Callback<TopRatedAPIData>
                    {
                        override fun onResponse(call: Call<TopRatedAPIData>, response: Response<TopRatedAPIData>) {
                            if (response.isSuccessful){

//                    var movieList = arrayOf<TopRatedMovies>()
//                    for(i in response.body()!!.results.){
//                        movieList.add()
//                    }

                                movieRecyclerView.apply {
                                    setHasFixedSize(true)
                                    layoutManager = GridLayoutManager(this@MainActivity,2)
                                    movieAdapter = MoviesMainAdapter(response.body()!!.results)
                                    movieRecyclerView.adapter = movieAdapter
//                                    println(response.body()!!.results[0])

                                    movieAdapter.setOnItemClickListener(object :MoviesMainAdapter.onItemClickListener{
                                        override fun onItemClick(position: Int) {

                                            Toast.makeText(applicationContext, "Clicked on ", Toast.LENGTH_SHORT).show()
                                            val intent = Intent(this@MainActivity,MoviePageActivity::class.java)
                                            intent.putExtra("position",position)
                                            intent.putExtra("MovieId",response.body()!!.results[position].id)
                                            println("Id = ${response.body()!!.results[position].id}")
                                            startActivity(intent)
                                        }

                                    })
                                }
                            }
                        }
                        override fun onFailure(call: Call<TopRatedAPIData>, t: Throwable) {
                            Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })



                }
                else{


                    val request = ServiceBuilder.buildService(TopRated::class.java)
                    val call = request.getTopRated(getString(R.string.api_key))

                    call.enqueue(object : Callback<TopRatedAPIData>
                    {
                        override fun onResponse(call: Call<TopRatedAPIData>, response: Response<TopRatedAPIData>) {
                            if (response.isSuccessful){

//                    var movieList = arrayOf<TopRatedMovies>()
//                    for(i in response.body()!!.results.){
//                        movieList.add()
//                    }

                                movieRecyclerView.apply {
                                    setHasFixedSize(true)
                                    layoutManager = GridLayoutManager(this@MainActivity,2)
                                    movieAdapter = MoviesMainAdapter(response.body()!!.results)
                                    movieRecyclerView.adapter = movieAdapter
//                                    println(response.body()!!.results[0])

                                    movieAdapter.setOnItemClickListener(object :MoviesMainAdapter.onItemClickListener{
                                        override fun onItemClick(position: Int) {

                                            Toast.makeText(applicationContext, "Clicked on ", Toast.LENGTH_SHORT).show()
                                            val intent = Intent(this@MainActivity,MoviePageActivity::class.java)
                                            intent.putExtra("position",position)
                                            intent.putExtra("MovieId",response.body()!!.results[position].id)
                                            println("Id = ${response.body()!!.results[position].id}")
                                            startActivity(intent)
                                        }

                                    })
                                }
                            }
                        }
                        override fun onFailure(call: Call<TopRatedAPIData>, t: Throwable) {
                            Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })



                }
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

}


