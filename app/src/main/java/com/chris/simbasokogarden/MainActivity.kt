package com.chris.simbasokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



//        Sign In intent
        val signin=findViewById<Button>(R.id.signin)

//        set on click listener
        signin.setOnClickListener {

            val signinIntent=Intent(applicationContext,SignIn::class.java)
            startActivity(signinIntent)
        }

        val aboutInfo=findViewById<Button>(R.id.aboutInfo)

        aboutInfo.setOnClickListener {

            val aboutInfoIntent= Intent(applicationContext, About::class.java)
            startActivity(aboutInfoIntent)
        }

        val progressbar=findViewById<ProgressBar>(R.id.progressbar)
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        val api="http://chriscollins.alwaysdata.net/api/getproductdetails"

        val helper= ApiHelper(applicationContext)
        helper.loadProducts(api,recyclerView,progressbar)




    }
}