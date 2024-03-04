package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen02.planetas.PlanetasActivity
import com.example.examen02.sistema.SistemaActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSistemas = findViewById<Button>(R.id.btnSistemas)
        val btnPlanetas = findViewById<Button>(R.id.btnPlanetas)

        btnSistemas.setOnClickListener {
            val intent = Intent(this, SistemaActivity::class.java)
            startActivity(intent)
        }
        btnPlanetas.setOnClickListener {
            val intent = Intent(this, PlanetasActivity::class.java)
            startActivity(intent)
        }
    }
}