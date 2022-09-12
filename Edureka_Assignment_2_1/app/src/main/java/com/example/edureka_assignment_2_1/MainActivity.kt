package com.example.edureka_assignment_2_1

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnGreen.setOnClickListener(){
                clTest.setBackgroundColor(Color.GREEN)

        }

        btnBlue.setOnClickListener(){
            clTest.setBackgroundColor(Color.BLUE)
        }

        btnRed.setOnClickListener(){
            clTest.setBackgroundColor(Color.RED)
        }

        btnGoogle.setOnClickListener(){

            intent = Intent(this,MainActivity2::class.java)
                intent.putExtra("EXTRA_Url","https://www.google.com/")
                startActivity(intent)


        }

        btnYahoo.setOnClickListener(){
            intent = Intent(this,MainActivity2::class.java)
                intent.putExtra("EXTRA_Url","https://www.yahoo.com")
                startActivity(intent)
        }

    }


}


