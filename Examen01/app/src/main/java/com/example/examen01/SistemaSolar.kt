package com.example.examen01

class SistemaSolar(
    var id: Int,
    var nombre: String,
    var descripcion: String,
    var cantidadSatelites: Int,
) {
    override fun toString(): String {
        return """
            ID: ${id}
            Nombre: ${nombre}
            Descripción: ${descripcion}
            Cantidad de satélites: ${cantidadSatelites}
        """.trimIndent()
    }
}