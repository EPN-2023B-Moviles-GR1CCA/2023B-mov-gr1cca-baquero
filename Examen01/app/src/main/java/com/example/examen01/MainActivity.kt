package com.example.examen01

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    val arreglo = MemoriaVirtual.arregloSistemasSolares
    var posicionItemSeleccionado = 0

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_sistema_solar, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_ver_planetas -> {
                val selectedItem = arreglo[posicionItemSeleccionado]
                val intent = Intent(this, PlanetaActivity::class.java)
                intent.putExtra("id", selectedItem.id.toString())
                startActivity(intent)
                return true
            }

            R.id.mi_editar -> {
                val selectedItem = arreglo[posicionItemSeleccionado]
                val intent = Intent(this, CrudSistemaSolar::class.java)
                intent.putExtra("id", selectedItem.id.toString())
                startActivity(intent)
                return true
            }
            R.id.mi_eliminar -> {
                mostrarSnackbar("${posicionItemSeleccionado}")
                //abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.lv_sistemas_solares)
        val adaptador = ArrayAdapter(
            this, //Contexto
            //como se va a ver (XML)
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)

        val botonNuevoSistemaSolar = findViewById<Button>(R.id.btn_nuevo_sistema_solar)

        botonNuevoSistemaSolar.setOnClickListener {
            irActividad(CrudSistemaSolar:: class.java)
        }
    }

    fun mostrarSnackbar(texto: String) {
        Snackbar
            .make(
                findViewById(R.id.cl_main),
                texto, //texto
                Snackbar.LENGTH_LONG //tiempo
            )
            .show()
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun abrirDialogo(id: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { dialog, which ->
                eliminarSistemaSolar(id)
                mostrarSnackbar("Sistema Solar eliminado")
            }
        )

        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    fun eliminarSistemaSolar(id: Int) {
        val listView = findViewById<ListView>(R.id.lv_sistemas_solares)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        var idSistemaSolarAEliminar = arreglo[id].id
        //CrudArtista().eliminarAlbumesDelArtista(idArtistaAEliminar)
        //arregloArtistas.removeAt(
        //    id
        //)
    }
}