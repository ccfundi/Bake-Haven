package com.chris.simbasokogarden

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class Payments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payments)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pay=findViewById<Button>(R.id.payment)
        val phone=findViewById<EditText>(R.id.phone)
        val name=findViewById<TextView>(R.id.product_name)
        val cost=findViewById<TextView>(R.id.product_cost)
        val photo=findViewById<ImageView>(R.id.product_photo)
        val description=findViewById<TextView>(R.id.product_description)

//        Receive/Retrieve Extras Data the product_name and product_cost
//        This data is passed via Intent
        val productname=intent.getStringExtra("product_name")
        val productcost=intent.getIntExtra("product_cost",0)
        val productphoto=intent.getStringExtra("product_photo")
        val productdescription=intent.getStringExtra("product_description")

//        Update TextViews with Values Passed Via Intent
        name.text= productname
        cost.text= "Ksh $productcost"
        description.text= productdescription
        Glide.with(this)
            .load(productphoto)
            .circleCrop()
            .into(photo)

        pay.setOnClickListener{
//            Set API Endpoint
            val api="http://chriscollins.alwaysdata.net/api/mpesa_payment"
//            Create data using RequestParams, put phone and cost as key value pairs
            val data = RequestParams()
            data.put("amount",productcost)
            data.put("phone",phone.text.toString())

//            Access API helper
            val helper= ApiHelper(applicationContext)
//            Post data to api endpoint
            helper.post(api,data)
        }




    }
}