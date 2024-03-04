package com.example.examen02.sistema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examen02.R
import com.example.examen02.models.SistemaSolar
import com.example.examen02.service.CRUDService
import java.lang.Exception

class AddSistemaActivity : AppCompatActivity() {
    private lateinit var crudService: CRUDService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_sistema)

        val editTextNombre = findViewById<EditText>(R.id.editTextNombreSistema)
        val editTextDescripcion = findViewById<EditText>(R.id.editTextDescripcionSistema)
        val editTextSatelites = findViewById<EditText>(R.id.editTextSatelites)
        val botonAgregar = findViewById<Button>(R.id.buttonAgregarSistema)

        botonAgregar.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val descripcion = editTextDescripcion.text.toString()
            val cantidadSatelites = editTextSatelites.text.toString().toIntOrNull() ?: 0

            val nuevoSistema = SistemaSolar(
                nombre, descripcion, cantidadSatelites
            )

            try {
                val crudService = CRUDService(this)
                crudService.addSistema(nuevoSistema)
                Toast.makeText(this, "Sistema creado con éxito", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al crear el sistema", Toast.LENGTH_SHORT).show()
            }
        }

    }
}