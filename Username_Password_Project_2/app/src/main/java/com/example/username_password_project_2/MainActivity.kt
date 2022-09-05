package com.example.username_password_project_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSubmit.setOnClickListener(){
            val username = etUsername?.text.toString()
            val password = etPassword?.text.toString()

            //println(username)
            //println(password)

            if(username!="" && password!=""){
                //println("Inside 1st if")
                if(username=="edureka" && password=="edureka123") {
                    //println("Inside 2nd if")
                    btnSubmit.isEnabled=false
                    Toast.makeText(applicationContext,"Correct username and Password",Toast.LENGTH_SHORT).show()
                }
                else{
                    //println("Inside else")
                    if(username!="edureka"){
                        etUsername.setError("Not a valid User Name")
                    }
                    else if(password!="edureka123"){
                        etPassword.setError("Password is not correct")
                    }
                    Toast.makeText(applicationContext,"Wrong UserName or Password",Toast.LENGTH_SHORT).show()
                }


            }
            else{
                Toast.makeText(applicationContext,"Username or password is empty!!",Toast.LENGTH_SHORT).show()
            }
        }


    }
}