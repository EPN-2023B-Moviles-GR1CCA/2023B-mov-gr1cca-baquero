package com.example.examen02.planetas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.examen02.R
import com.example.examen02.models.Planeta
import com.example.examen02.models.SistemaSolar
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddPlanetaActivity : AppCompatActivity() {
    private lateinit var spinnerSistemas: Spinner
    private lateinit var editTextNombre: EditText
    private lateinit var editTextDescripcion: EditText
    private lateinit var editTextPosicion: EditText
    private lateinit var checkboxTieneVida: CheckBox

    private lateinit var crudService: CRUDService
    private var sistemas: List<SistemaSolar> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_planeta)

        spinnerSistemas = findViewById(R.id.spinnerSistemas)
        editTextNombre = findViewById(R.id.editTextNombrePlaneta)
        editTextDescripcion = findViewById(R.id.editTextDescripcionPlaneta)
        editTextPosicion = findViewById(R.id.editTextPosicionPlaneta)
        checkboxTieneVida = findViewById(R.id.checkBoxTieneVida)

        crudService = CRUDService(this)

        cargarSistemas()
        configurarSpinnerSistemas()
    }

    private fun cargarSistemas() {
        CoroutineScope(Dispatchers.Main).launch {
            sistemas = crudService.leerSistemas()
            val adapter = ArrayAdapter(
                this@AddPlanetaActivity,
                android.R.layout.simple_spinner_item,
                sistemas.map { it.nombre })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerSistemas.adapter = adapter
        }
    }

    private fun configurarSpinnerSistemas() {
        spinnerSistemas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    fun onAgregarPlanetaClick(view: View) {
        val sistemaSeleccionado = sistemas[spinnerSistemas.selectedItemPosition]
        val nombre = editTextNombre.text.toString()
        val descripcion = editTextDescripcion.text.toString()
        val posicion = editTextPosicion.text.toString().toIntOrNull() ?: 1
        val tieneVida = checkboxTieneVida.isChecked

        val planeta = Planeta(nombre, descripcion, posicion, tieneVida)
        sistemaSeleccionado.agregarPlaneta(planeta)
        crudService.actualizarSistema(sistemaSeleccionado.nombre, sistemaSeleccionado)
        crudService.addPlaneta(planeta)

        Toast.makeText(this, "Planeta agregado al sistema ${sistemaSeleccionado.nombre}", Toast.LENGTH_SHORT).show()
    }

}