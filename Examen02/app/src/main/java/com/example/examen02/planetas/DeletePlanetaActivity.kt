package com.example.examen02.planetas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.example.examen02.R
import com.example.examen02.models.Planeta
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class DeletePlanetaActivity : AppCompatActivity() {
    private lateinit var spinnerPlanetas: Spinner
    private lateinit var botonEliminar: Button
    private lateinit var crudService: CRUDService
    private var planetas: List<Planeta> = listOf()
    private var planetaSeleccionado: Planeta? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_planeta)

        spinnerPlanetas = findViewById(R.id.spinnerPlanetas)
        botonEliminar = findViewById(R.id.botonEliminar)
        crudService = CRUDService(this)

        cargarPlanetas()
        configurarSpinnerPlanetas()
    }

    private fun cargarPlanetas() {
        CoroutineScope(Dispatchers.Main).launch {
            planetas = crudService.leerPlanetas()
            val adapter = ArrayAdapter(
                this@DeletePlanetaActivity,
                android.R.layout.simple_spinner_item,
                planetas.map { it.nombre })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerPlanetas.adapter = adapter
        }
    }

    private fun configurarSpinnerPlanetas() {
        spinnerPlanetas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                planetaSeleccionado = planetas[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                planetaSeleccionado = null
            }
        }
    }

    fun onEliminarPlanetaClick(view: View) {
        planetaSeleccionado?.let {
            try {
                crudService.eliminarPlaneta(it.nombre)
                Toast.makeText(this, "Planeta eliminado con éxito", Toast.LENGTH_SHORT).show()
                cargarPlanetas()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al eliminar el planeta", Toast.LENGTH_SHORT).show()
            }
        } ?: Toast.makeText(this, "Por favor selecciona un planeta", Toast.LENGTH_SHORT).show()
    }
}