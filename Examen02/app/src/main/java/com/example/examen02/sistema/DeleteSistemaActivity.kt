package com.example.examen02.sistema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen02.R
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteSistemaActivity : AppCompatActivity() {

    private lateinit var  recyclerView: RecyclerView
    private lateinit var adaptador: SistemasEliminarAdapter
    private lateinit var crudService: CRUDService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_sistema)

        recyclerView = findViewById(R.id.sistemasEliminarView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        crudService = CRUDService(this)

        actualizarLista()
    }

    private fun eliminarSistema(nombre: String) {
        crudService.eliminarSistema(nombre)
        actualizarLista()
    }

    private fun actualizarLista() {
        CoroutineScope(Dispatchers.Main).launch {
            val sistemas = crudService.leerSistemas()
            adaptador = SistemasEliminarAdapter(sistemas, this@DeleteSistemaActivity::eliminarSistema)
            recyclerView.adapter = adaptador
        }
    }
}