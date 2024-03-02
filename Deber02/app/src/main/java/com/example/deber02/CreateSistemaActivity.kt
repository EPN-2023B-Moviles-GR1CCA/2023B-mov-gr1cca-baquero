package com.example.deber02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class CreateSistemaActivity : AppCompatActivity() {

    var idPlaneta = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_sistema)

        idPlaneta = intent.getStringExtra("idPlaneta").toString()
        mostrarSnackbar("ID del Planeta: $idPlaneta")

        val btnCrearSistema = findViewById<Button>(R.id.btn_crear_sistema)
        btnCrearSistema.setOnClickListener {
            crearSistema()
        }

        val btnBack = findViewById<Button>(R.id.btn_back_create_sistema)
        btnBack.setOnClickListener {
            val intent = Intent(this, VerPlanetasActivity::class.java)
            intent.putExtra("id", idPlaneta)
            startActivity(intent)
        }
    }

    fun crearSistema() {
        val id = findViewById<EditText>(R.id.input_id_sistema)
        val nombre = findViewById<EditText>(R.id.input_nombre_sistema)
        val descripcion = findViewById<EditText>(R.id.input_descripcion_sistema)
        val cantidadSatelites = findViewById<EditText>(R.id.input_satelites_sistema)

        mostrarSnackbar("Se ha creado el sistema ${nombre.text} y el ID del planeta es ${idPlaneta}")

        val intent = Intent(this, VerPlanetasActivity::class.java)
        intent.putExtra("id", idPlaneta)
        startActivity(intent)
    }

    fun mostrarSnackbar(texto: String) {
        Snackbar.make(
            findViewById(R.id.form_create_album),
            texto,
            Snackbar.LENGTH_LONG
        ).show()
    }
}