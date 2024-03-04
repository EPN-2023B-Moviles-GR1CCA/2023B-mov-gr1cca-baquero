package com.example.examen02.planetas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen02.R

class PlanetasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planetas)

        val btnAddPlaneta = findViewById<Button>(R.id.btnAddPlaneta)
        val btnReadPlaneta = findViewById<Button>(R.id.btnReadPlanetas)
        val btnDeletePlaneta = findViewById<Button>(R.id.btnDeletePlaneta)
        val btnUpdatePlaneta = findViewById<Button>(R.id.btnUpdatePlaneta)

        btnAddPlaneta.setOnClickListener {
            val intent = Intent(this, AddPlanetaActivity::class.java)
            startActivity(intent)
        }
        btnReadPlaneta.setOnClickListener {
            val intent = Intent(this, ReadPlanetaActivity::class.java)
            startActivity(intent)
        }
        btnDeletePlaneta.setOnClickListener {
            val intent = Intent(this, DeletePlanetaActivity::class.java)
            startActivity(intent)
        }
        btnUpdatePlaneta.setOnClickListener {
            val intent = Intent(this, UpdatePlanetaActivity::class.java)
            startActivity(intent)
        }
    }
}