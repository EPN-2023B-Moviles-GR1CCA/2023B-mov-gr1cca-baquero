package com.example.examen02.planetas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.examen02.R
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReadPlanetaActivity : AppCompatActivity() {
    private lateinit var listViewPlanetas : ListView
    private lateinit var crudService: CRUDService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_planeta)

        listViewPlanetas = findViewById(R.id.listViewPlanetas)
        crudService = CRUDService(this)

        cargarPlanetas()
    }

    private fun cargarPlanetas() {
        CoroutineScope(Dispatchers.Main).launch {
            val planetas = crudService.leerPlanetas()
            println("PLANETAS" + planetas)
            val adapter = ArrayAdapter(this@ReadPlanetaActivity, android.R.layout.simple_list_item_1, planetas.map { "${it.nombre} - ${it.descripcion}" })
            listViewPlanetas.adapter = adapter
        }
    }
}