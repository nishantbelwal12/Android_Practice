package com.example.a3_2_edurekaassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val leftFragment = LeftFragment()
        val rightFragment = RightFragment()

        supportFragmentManager.beginTransaction().apply {

            replace(R.id.flLeft,leftFragment)
            replace(R.id.flRight,rightFragment)
            commit()
        }



    }
}