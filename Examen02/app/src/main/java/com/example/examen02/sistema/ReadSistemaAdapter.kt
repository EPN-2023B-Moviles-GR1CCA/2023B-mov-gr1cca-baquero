package com.example.examen02.sistema

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen02.R
import com.example.examen02.models.SistemaSolar

class ReadSistemaAdapter(private val sistemas: List<SistemaSolar>) : RecyclerView.Adapter<ReadSistemaAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.nombreTextView)
        val descripcionTextView : TextView = view.findViewById(R.id.descripcionSistemaTextView)
        val cantidadSatelites : TextView = view.findViewById(R.id.satelitesSistemaTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sistema_read_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sistema = sistemas[position]
        holder.nombreTextView.text = sistema.nombre
        holder.descripcionTextView.text = sistema.descripcion
        holder.cantidadSatelites.text = sistema.cantidadSatelites.toString()
    }

    override fun getItemCount() = sistemas.size
}