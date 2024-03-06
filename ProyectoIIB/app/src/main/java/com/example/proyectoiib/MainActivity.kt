package com.example.proyectoiib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var questionNo = 0
    var questions = listOf(
        "Cuáles son los 2 principales lenguajes de desarrollo Android \n\n A) Kotlin y Java \n\n B) Java y Python \n\n C) Kotlin y Python",
        "Cómo se define una función en Kotlin? \n\n A) fun \n\n B) var \n\n C) function",
        "Para qué es usada una variable? \n\n A) Para almacenar información \n\n B) Para guardar un valor aleatorio \n\n C) No se",
        "Qué significa SDK en Android SDK? \n\n A) Software Development Kit \n\n B) Software Development Kotlin \n\n C) Otro significado"
    )
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        textView = findViewById<TextView>(R.id.textView)

        button.setOnClickListener {
            showToast(1)
        }

        button2.setOnClickListener {
            showToast(2)
        }

        button3.setOnClickListener {
            showToast(3)
        }
    }

    fun updateQuestion() {
        questionNo = questionNo + 1
        textView.setText(questions.get(questionNo))
    }

    fun showToast(answer: Int) {
        if (answer == 1) {
            Toast.makeText(applicationContext, "Correcto!", Toast.LENGTH_SHORT).show()
            updateQuestion()
        } else {
            Toast.makeText(applicationContext, "Incorrecto", Toast.LENGTH_SHORT).show()
        }
    }
}