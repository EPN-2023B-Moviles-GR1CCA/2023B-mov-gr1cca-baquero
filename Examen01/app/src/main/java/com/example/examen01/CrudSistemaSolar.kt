package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.google.android.material.snackbar.Snackbar

class CrudSistemaSolar : AppCompatActivity() {

    val arregloSistemasSolares = MemoriaVirtual.arregloSistemasSolares
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_sistema_solar)

        val botonGuardarSistemaSolar = findViewById<Button>(R.id.btn_guardar_sistema_solar)
        botonGuardarSistemaSolar
            .setOnClickListener {
                val id = findViewById<EditText>(R.id.input_id_sistema_solar)
                val nombre = findViewById<EditText>(R.id.input_nombre_sistema_solar)
                val descripcion = findViewById<EditText>(R.id.input_descripcion_sistema_solar)
                val satelites = findViewById<EditText>(R.id.input_cantidad_sistema_solar)
                arregloSistemasSolares.forEachIndexed { index, sistemaSolar ->
                    if (id.text.toString().toInt() == sistemaSolar.id) {
                        arregloSistemasSolares[index] = SistemaSolar(
                            id.text.toString().toInt(),
                            nombre.text.toString(),
                            descripcion.text.toString(),
                            satelites.text.toString().toInt()
                        )
                        mostrarSnackbar("Sistema Solar Editado")
                    } else {
                        arregloSistemasSolares.add(
                            SistemaSolar(
                                id.text.toString().toInt(),
                                nombre.text.toString(),
                                descripcion.text.toString(),
                                satelites.text.toString().toInt()
                            )
                        )
                        mostrarSnackbar("Sistema Solar Creado")
                    }
                }

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("id", id.text.toString())
                startActivity(intent)
            }
    }

    fun mostrarSnackbar(texto: String) {
        Snackbar
            .make(
                findViewById(R.id.cl_crud_sistema_solar),
                texto, //texto
                Snackbar.LENGTH_LONG //tiempo
            )
            .show()
    }
}