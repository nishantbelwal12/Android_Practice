package com.example.edureka_assignment_3_1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    btnSubmit.setOnClickListener(){

        var username = etUsername?.text.toString()
        var password = etPassword?.text.toString()

        if(username=="edureka" && password == "edureka123"){
            ivAuthenticate.setImageResource(R.drawable.sucess)
            tvAuthenticate.text = "Success"
            tvAuthenticate.setTextColor(R.color.green)
        }
        else{
            ivAuthenticate.setImageResource(R.drawable.fail)
            tvAuthenticate.text = "Failure"
            tvAuthenticate.setTextColor(R.color.red)
        }
    }

    }
}