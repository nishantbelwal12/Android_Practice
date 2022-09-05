package com.example.toast_project_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(applicationContext, "Currently on OnCreate", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()

        Toast.makeText(applicationContext,"Currently on OnStart Mode", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()

        Toast.makeText(applicationContext,"Currently on OnResume Mode",Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()

        Toast.makeText(applicationContext,"Currently on OnPause mode",Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()

        Toast.makeText(applicationContext,"Currently on OnRestart mode",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()

        Toast.makeText(applicationContext,"Currently on OnDestroy mode",Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()

        Toast.makeText(applicationContext,"Curently on OnStop mode",Toast.LENGTH_SHORT).show()
    }
}