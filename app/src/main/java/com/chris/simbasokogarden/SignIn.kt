package com.chris.simbasokogarden

import android.content.Intent
import android.os.Bundle
import android.view.PixelCopy
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
//        http://chriscollins.alwaysdata.net/api/signin
//        http://chriscollins.alwaysdata.net/api/signup
//        http://chriscollins.alwaysdata.net/api/mpesa_payment
//        http://chriscollins.alwaysdata.net/api/getproductdetails


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signin=findViewById<TextView>(R.id.signin_link)

        signin.setOnClickListener {
            val signInlink= Intent(applicationContext, SignUp::class.java)
            startActivity(signInlink)
        }

        val email=findViewById<EditText>(R.id.email)
        val password=findViewById<EditText>(R.id.password)
        val signinbutton=findViewById<Button>(R.id.signin)

        signinbutton.setOnClickListener {

            val api="http://chriscollins.alwaysdata.net/api/signin"

//            request params is the container used to collect the user details its like form data in js
            val data = RequestParams()

            data.put("email",email.text.toString())
            data.put("password",password.text.toString().trim())

//            Api helper-it delivers our data to the api
            val helper=ApiHelper(applicationContext)
            helper.post_login(api,data)
        }


    }
}