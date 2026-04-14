package com.chris.simbasokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signup=findViewById<TextView>(R.id.signup_link)

        signup.setOnClickListener {
            val signUplink= Intent(applicationContext, SignIn::class.java)
            startActivity(signUplink)
        }

        val signupbutton=findViewById<Button>(R.id.signup)
        val username=findViewById<EditText>(R.id.name)
        val email=findViewById<EditText>(R.id.email)
        val phone=findViewById<EditText>(R.id.phone)
        val password=findViewById<EditText>(R.id.password)

        signupbutton.setOnClickListener {

            val api="https://bridgit.alwaysdata.net/api/signup"

            val data= RequestParams()

            data.put("username",username.text.toString())
            data.put("email",email.text.toString())
            data.put("phone",phone.toString())
            data.put("password",password.text.toString())

           val helper= ApiHelper(applicationContext)
            helper.post(api,data)
        }
    }
}