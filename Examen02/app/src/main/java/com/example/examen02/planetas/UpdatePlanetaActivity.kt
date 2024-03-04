package com.example.examen02.planetas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.examen02.R
import com.example.examen02.models.Planeta
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdatePlanetaActivity : AppCompatActivity() {
    private lateinit var spinnerPlanetas: Spinner
    private lateinit var editTextNombre: EditText
    private lateinit var editTextDescripcion: EditText
    private lateinit var editTextPosicion: EditText
    private lateinit var checkboxTieneVida: CheckBox
    private lateinit var botonActualizar: Button

    private lateinit var crudService: CRUDService
    private var planetas: List<Planeta> = listOf()
    private var planetaSeleccionado: Planeta? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_planeta)

        spinnerPlanetas = findViewById(R.id.spinnerPlanetasUpdate)
        editTextNombre = findViewById(R.id.editTextNombrePlanetaUpdate)
        editTextDescripcion = findViewById(R.id.editTextDescripcionPlanetaUpdate)
        editTextPosicion = findViewById(R.id.editTextPosicionPlanetaUpdate)
        checkboxTieneVida = findViewById(R.id.checkBoxTieneVidaPlanetaUpdate)
        botonActualizar = findViewById(R.id.botonActualizarPlaneta)

        crudService = CRUDService(this)

        cargarPlanetas()
        configurarSpinnerPlanetas()
    }

    private fun cargarPlanetas() {
        CoroutineScope(Dispatchers.Main).launch {
            planetas = crudService.leerPlanetas()
            val adapter = ArrayAdapter(
                this@UpdatePlanetaActivity,
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
                cargarDatosPlaneta(planetaSeleccionado!!)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                planetaSeleccionado = null
            }
        }
    }

    private fun cargarDatosPlaneta(planeta: Planeta) {
        editTextNombre.setText(planeta.nombre)
        editTextDescripcion.setText(planeta.descripcion)
        editTextPosicion.setText(planeta.posicion.toString())
        checkboxTieneVida.isChecked = planeta.tieneVida
    }

    fun onActualizarPlanetaClick(view: View) {
        planetaSeleccionado?.let {
            val nombre = editTextNombre.text.toString()
            val descripcion = editTextDescripcion.text.toString()
            val posicion = editTextPosicion.text.toString().toIntOrNull() ?: 1
            val tieneVida = checkboxTieneVida.isChecked

            val planetaActualizado = Planeta(
                nombre, descripcion, posicion, tieneVida
            )

            try {
                crudService.actualizarPlaneta(it.nombre, planetaActualizado)
                Toast.makeText(this, "Planeta actualizado con éxito!", Toast.LENGTH_SHORT).show()
                cargarPlanetas()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al actualizar el planeta", Toast.LENGTH_SHORT).show()
            }
        } ?: Toast.makeText(this, "Por favor selecciona un planeta", Toast.LENGTH_SHORT).show()
    }
}