package com.example.examen02.sistema

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen02.R
import com.example.examen02.models.SistemaSolar

class SistemasEliminarAdapter(
    private val sistemas: List<SistemaSolar>,
    private val onEliminarClicked: (String) -> Unit
) : RecyclerView.Adapter<SistemasEliminarAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreSistemaTextView: TextView = view.findViewById(R.id.nombreSistemaTextView)
        val eliminarBoton: Button = view.findViewById(R.id.buttonEliminarSistema)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sistema_delete_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sistema = sistemas[position]
        holder.nombreSistemaTextView.text = sistema.nombre
        holder.eliminarBoton.setOnClickListener { onEliminarClicked(sistema.nombre) }
    }

    override fun getItemCount() = sistemas.size
}