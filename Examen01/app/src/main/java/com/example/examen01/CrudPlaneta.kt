package com.example.examen01

import android.content.Intent
import android.media.Image.Plane
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class CrudPlaneta : AppCompatActivity() {

    var arregloPlanetas = MemoriaVirtual.arregloPlanetas
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_planeta)

        val botonGuardarPlaneta = findViewById<Button>(R.id.btn_guardar_planeta)
        botonGuardarPlaneta
            .setOnClickListener {
                val id = findViewById<EditText>(R.id.input_id_planeta)
                val nombre = findViewById<EditText>(R.id.input_nombre_planeta)
                val descripcion = findViewById<EditText>(R.id.input_descripcion_planeta)
                val posicion = findViewById<EditText>(R.id.input_posicion_planeta)
                val tieneVida = findViewById<CheckBox>(R.id.input_vida_planeta).isChecked
                arregloPlanetas.forEachIndexed { index, planeta ->
                    if (id.text.toString().toInt() == planeta.id) {
                        arregloPlanetas[index] = Planeta(
                            id.text.toString().toInt(),
                            nombre.text.toString(),
                            descripcion.text.toString(),
                            posicion.text.toString().toInt(),
                            tieneVida
                        )
                        mostrarSnackbar("Planeta Editado")
                    } else {
                        arregloPlanetas.add(
                            Planeta(
                                id.text.toString().toInt(),
                                nombre.text.toString(),
                                descripcion.text.toString(),
                                posicion.text.toString().toInt(),
                                tieneVida
                            )
                        )
                        mostrarSnackbar("Planeta Creado")
                    }
                }

                val intent = Intent(this, PlanetaActivity::class.java)
                intent.putExtra("id", id.text.toString())
                startActivity(intent)
            }
    }

    fun mostrarSnackbar(texto: String) {
        Snackbar
            .make(
                findViewById(R.id.cl_crud_planeta),
                texto, //texto
                Snackbar.LENGTH_LONG //tiempo
            )
            .show()
    }

}