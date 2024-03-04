package com.example.examen02.models

data class Planeta(
    var nombre: String,
    var descripcion: String,
    var posicion: Int,
    var tieneVida: Boolean
) {
    override fun toString(): String {
        return """
            Nombre: ${nombre}
            Descripción: ${descripcion}
            Posición en el Sistema Solar: ${posicion}
            Tiene vida? ${tieneVida}
        """.trimIndent()
    }
}