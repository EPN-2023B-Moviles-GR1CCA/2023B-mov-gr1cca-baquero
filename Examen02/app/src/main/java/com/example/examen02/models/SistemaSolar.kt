package com.example.examen02.models

data class SistemaSolar(
    var nombre: String,
    var descripcion: String,
    var cantidadSatelites: Int,
    var planetas: MutableList<Planeta> = mutableListOf()
) {
    override fun toString(): String {
        return """
            Nombre: ${nombre}
            Descripción: ${descripcion}
            Cantidad de satélites: ${cantidadSatelites}
        """.trimIndent()
    }

    fun agregarPlaneta(planeta: Planeta) {
        planetas.add(planeta)
    }
}