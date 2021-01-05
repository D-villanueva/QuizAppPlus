package com.fiuady.quizappplus

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var TextInicio:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TextInicio=findViewById(R.id.titulo)
        TextInicio.typeface= Typeface.createFromAsset(assets,"fonts/Balamoa.ttf")
    }
}