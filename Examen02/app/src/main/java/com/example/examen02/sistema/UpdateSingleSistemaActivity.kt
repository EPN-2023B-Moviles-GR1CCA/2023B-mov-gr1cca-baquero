package com.example.examen02.sistema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.examen02.R
import com.example.examen02.models.SistemaSolar
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateSingleSistemaActivity : AppCompatActivity() {
    private lateinit var editTextNombre: EditText
    private lateinit var editTextDescripcion: EditText
    private lateinit var editTextSatelites: EditText
    private lateinit var buttonActualizar: Button
    private var crudService = CRUDService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_single_sistema)

        editTextNombre = findViewById(R.id.editTextNombre)
        editTextDescripcion = findViewById(R.id.editTextDescripcion)
        editTextSatelites = findViewById(R.id.editTextSatelitesSistema)
        buttonActualizar = findViewById(R.id.buttonActualizar)

        val nombreSistema = intent.getStringExtra("nombreSistema")
        cargarDatosSistema(nombreSistema)

        buttonActualizar.setOnClickListener {
            val nuevoSistema = SistemaSolar(
                nombre = editTextNombre.text.toString(),
                descripcion = editTextDescripcion.text.toString(),
                cantidadSatelites = editTextSatelites.text.toString().toIntOrNull() ?: 0
            )

            crudService.actualizarSistema(nombreSistema ?: "", nuevoSistema)
            finish()
        }
    }

    private fun cargarDatosSistema(nombreSistema: String?) {
        nombreSistema?.let { nombre ->
            CoroutineScope(Dispatchers.Main).launch {
                val sistemas = crudService.leerSistemas()
                val sistema = sistemas.find { it.nombre == nombre }
                sistema?.let {
                    editTextNombre.setText(sistema.nombre)
                    editTextDescripcion.setText(sistema.descripcion)
                    editTextSatelites.setText(sistema.cantidadSatelites.toString())
                }
            }
        }
    }
}