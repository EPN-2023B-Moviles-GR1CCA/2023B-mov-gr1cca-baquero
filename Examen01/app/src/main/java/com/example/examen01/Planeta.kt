package com.example.examen01

import java.util.Date

class Planeta(
    var id: Int,
    var nombre: String,
    var descripcion: String,
    var posicion: Int,
    var tieneVida: Boolean
) {
    override fun toString(): String {
        return """
            ID: ${id}
            Nombre: ${nombre}
            Descripción: ${descripcion}
            Posición en el Sistema Solar: ${posicion}
            Tiene vida? ${tieneVida}
        """.trimIndent()
    }
}