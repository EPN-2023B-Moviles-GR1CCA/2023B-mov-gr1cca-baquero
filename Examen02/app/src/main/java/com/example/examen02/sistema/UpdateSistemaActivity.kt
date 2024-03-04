package com.example.examen02.sistema

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.examen02.R
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateSistemaActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val crudService = CRUDService(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_sistema)

        listView = findViewById(R.id.listViewSistemas)
        mostrarSistemas()
    }

    private fun mostrarSistemas() {
        CoroutineScope(Dispatchers.Main).launch {
            val sistemas = crudService.leerSistemas()
            val adapter = ArrayAdapter(this@UpdateSistemaActivity, android.R.layout.simple_list_item_1, sistemas.map { it.nombre })
            listView.adapter = adapter

            listView.setOnItemClickListener { _, _, position, _ ->
                val intent = Intent(this@UpdateSistemaActivity, UpdateSingleSistemaActivity::class.java).apply {
                    putExtra("nombreSistema", sistemas[position].nombre)
                }
                startActivity(intent)
            }
        }
    }
}