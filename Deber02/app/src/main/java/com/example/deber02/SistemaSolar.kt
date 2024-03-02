package com.example.deber02

class SistemaSolar(
    var id: Int,
    var nombre: String,
    var descripcion: String,
    var cantidadSatelites: Int,
    var planeta: Planeta,
) {
    override fun toString(): String {
        return """
            ID: ${id}
            Nombre: ${nombre}
            Descripción: ${descripcion}
            Cantidad de satélites: ${cantidadSatelites}
            Planeta: ${planeta}
        """.trimIndent()
    }
}