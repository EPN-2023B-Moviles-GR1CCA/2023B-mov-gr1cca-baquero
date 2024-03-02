package com.example.deber02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    var sistemas = arrayListOf<SistemaSolar>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        BDD.bddAplicacion = SqliteSistemaSolarHelper(this)
        sistemas = BDD.bddAplicacion!!.obtenerSistemasSolares()

        if (sistemas.size != 0){
            val listView = findViewById<ListView>()
        }
    }
}