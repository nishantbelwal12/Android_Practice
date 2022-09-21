package com.example.bookmyshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyshow.Adapters.BestSellerAdapter
import com.example.bookmyshow.Adapters.MovieLayoutAdapter
import com.example.bookmyshow.Adapters.OfferLayoutAdapter
import com.example.bookmyshow.DataClasses.BestSellerItem
import com.example.bookmyshow.DataClasses.MovieItem
import com.example.bookmyshow.DataClasses.OfferItem

class MainActivity : AppCompatActivity() {

//    private lateinit var viewPager: ViewPager2
//    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var offerRecyclerView : RecyclerView
    private lateinit var bestSellerRecyclerView: RecyclerView
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var offeritem:ArrayList<OfferItem>
    private lateinit var bestselleritem:ArrayList<BestSellerItem>
    private lateinit var movieitem:ArrayList<MovieItem>

    lateinit var movieimage:Array<Int>
    lateinit var offerimage:Array<Int>
    lateinit var bestsellerimage:Array<Int>
    lateinit var moviename:Array<String>
    lateinit var bestsellername:Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewPager = findViewById(R.id.viewPager)

        movieimage = arrayOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5
        )
        offerimage = movieimage
        bestsellerimage = movieimage

        moviename = arrayOf(

            "Movie1",
            "Movie2",
            "Movie3",
            "Movie4",
            "Movie5",
            "Movie6",
            "Movie7",
            "Movie8",

        )
        bestsellername = moviename


        offerRecyclerView = findViewById(R.id.offerRecyclerView)
        bestSellerRecyclerView = findViewById(R.id.bestSellerRecyclerView)
        movieRecyclerView = findViewById(R.id.movieRecyclerView)

        offerRecyclerView.setHasFixedSize(true)
        bestSellerRecyclerView.setHasFixedSize(true)
        movieRecyclerView.setHasFixedSize(true)

        offerRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        bestSellerRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        movieRecyclerView.layoutManager = GridLayoutManager(this,2)


        offeritem = arrayListOf<OfferItem>()
        bestselleritem = arrayListOf<BestSellerItem>()
        movieitem = arrayListOf<MovieItem>()

        getOfferItem()
        getBestSellerItem()
        getMovieItem()



//        val adapter = ViewPagerAdapter(images)
//        viewPager.adapter = adapter
//        viewPager.beginFakeDrag()
//        viewPager.fakeDragBy(-5f)
//        viewPager.endFakeDrag()



    }

    private fun getMovieItem() {
        for(i in movieimage.indices){
            val item = MovieItem(moviename[i],movieimage[i])
            movieitem.add(item)
        }
        var adapter = MovieLayoutAdapter(movieitem)
        movieRecyclerView.adapter = adapter
        adapter.setonMovieClickListener(object : MovieLayoutAdapter.onMovieItemClickListener{
            override fun onMovieClick(position: Int) {
                Toast.makeText(this@MainActivity, "Cicked on ${movieitem[position].name}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getBestSellerItem() {
        for(i in bestsellerimage.indices){
            val item = BestSellerItem(bestsellername[i],bestsellerimage[i])
            bestselleritem.add(item)
        }
        bestSellerRecyclerView.adapter = BestSellerAdapter(bestselleritem)
    }

    private fun getOfferItem() {
        for(i in offerimage.indices){
            val item = OfferItem(offerimage[i])
            offeritem.add(item)
        }
        offerRecyclerView.adapter = OfferLayoutAdapter(offeritem)
    }
}