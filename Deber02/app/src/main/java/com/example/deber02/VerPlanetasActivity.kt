package com.example.deber02

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class VerPlanetasActivity : AppCompatActivity() {

    var arregloSistemas = BDD.bddAplicacion?.obtenerSistemasSolares() ?: emptyList()
    var posicionItemSeleccionado = -1
    var arregloPlanetasPorSistemaSolar = arrayListOf<SistemaSolar>()

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_planeta, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_sistema -> {
                val intent = Intent(this, EditSistemaActivity::class.java)
                intent.putExtra("idAlbum", arregloPlanetasPorSistemaSolar[posicionItemSeleccionado].id)
                startActivity(intent)
                return true
            }
            R.id.mi_eliminar_sistema -> {
                abrirDialogo(posicionItemSeleccionado)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_planetas)

        val idPlaneta = intent.getStringExtra("id")
        mostrarSnackbar("Ver planeta $idPlaneta")

        arregloPlanetasPorSistemaSolar = arregloSistemas.filter { sistema -> sistema.planeta.id == idPlaneta?.toInt() } as ArrayList<SistemaSolar>

        val listView = findViewById<ListView>(R.id.lv_list_planetas)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloPlanetasPorSistemaSolar
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)

        val btnCrearSistema = findViewById<Button>(R.id.btn_crear_sistema)
        btnCrearSistema.setOnClickListener {
            val intentCreate = Intent(this, CreateSistemaActivity::class.java)
            intentCreate.putExtra("idPlaneta", idPlaneta)
            startActivity(intentCreate)
        }

        val btnBack = findViewById<Button>(R.id.btn_back_ver_planetas)
        btnBack.setOnClickListener {
            irActividad(MainActivity::class.java)
        }
    }


    fun mostrarSnackbar(texto: String) {
        Snackbar
            .make(
                findViewById(R.id.id_layout_intents),
                texto,
                Snackbar.LENGTH_LONG
            )
            .show()
    }
    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun eliminarSistema(id: Int) {
        val listView = findViewById<ListView>(R.id.lv_list_planetas)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloPlanetasPorSistemaSolar
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        val idSistemaAEliminar = arregloPlanetasPorSistemaSolar[id].id

        BDD.bddAplicacion?.eliminarSistemaPorId(idSistemaAEliminar)
        arregloPlanetasPorSistemaSolar.removeAt(id)
    }


    fun abrirDialogo(id: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { dialog, which ->
                eliminarSistema(id)
                mostrarSnackbar("Álbum eliminado")
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val dialogo = builder.create()
        dialogo.show()
    }

}