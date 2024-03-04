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

class ReadSistemaActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var sistemasReadAdapter: ReadSistemaAdapter
    private lateinit var crudService: CRUDService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_sistema)

        recyclerView = findViewById(R.id.sistemasRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        crudService = CRUDService(this)

        CoroutineScope(Dispatchers.Main).launch {
            val sistemas = crudService.leerSistemas()

            println("********** SISTEMAS: " + sistemas)

            sistemasReadAdapter = ReadSistemaAdapter(sistemas)
            recyclerView.adapter = sistemasReadAdapter
        }
    }
}