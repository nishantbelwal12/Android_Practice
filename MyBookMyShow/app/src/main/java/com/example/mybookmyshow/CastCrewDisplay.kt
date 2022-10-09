package com.example.mybookmyshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.mybookmyshow.APIInterface.CastCrewAbout
import com.example.mybookmyshow.DataClass.CastCrewAbout.CastCrewAboutAPIData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CastCrewDisplay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casr_crew_display)

        val bundle: Bundle?= intent.extras
        val castCrewProfile:ImageView = findViewById(R.id.ivCastCrewAboutPage)
        val castCrewName:TextView = findViewById(R.id.tvCastCrewNameAboutPage)
        val castCrewAbout: TextView = findViewById(R.id.tvCastCrewAboutinAboutPage)

        val castCrewId = bundle?.getInt("CastCrewId")




        val request = ServiceBuilder.buildService(CastCrewAbout::class.java)
        val call = castCrewId?.let { request.getAbout(it,getString(R.string.api_key)) }

        if (call != null) {
            call.enqueue(object : Callback<CastCrewAboutAPIData>
            {
                override fun onResponse(call: Call<CastCrewAboutAPIData>, response: Response<CastCrewAboutAPIData>) {
                    if (response.isSuccessful){

                        val imagePath = "http://image.tmdb.org/t/p/w500${response.body()!!.profile_path}"
                        Glide.with(baseContext).load(imagePath).dontAnimate().into(castCrewProfile)
                        castCrewName.text = response.body()!!.name
                        castCrewAbout.text = response.body()!!.biography
    //
                    }
                }
                override fun onFailure(call: Call<CastCrewAboutAPIData>, t: Throwable) {
                    Toast.makeText(this@CastCrewDisplay, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }


    }
}