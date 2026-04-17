package com.chris.simbasokogarden

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class About : AppCompatActivity() {
//    Declare a tts variable
    lateinit var tts: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val abouttext=findViewById<TextView>(R.id.textView)
        val inputText=findViewById<EditText>(R.id.inputText)
        val speakButton=findViewById<Button>(R.id.speakButton)

//        Initialize TextToSpeech
        tts= TextToSpeech(this){
//            Check if the speech is successful
            if(it== TextToSpeech.SUCCESS){
                tts.language= Locale.US
            }
        }

//        Set Button Listener
        speakButton.setOnClickListener {

            val textt=abouttext.text.toString()
            tts.speak(textt, TextToSpeech.QUEUE_FLUSH,null,null)
        }


    }

//    Stop the tts from speaking when the app is closed/destroyed/killed
    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}