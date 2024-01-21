package com.example.examen01

class MemoriaVirtual {
    companion object {
        val arregloPlanetas = arrayListOf<Planeta>()
        val arregloSistemasSolares = arrayListOf<SistemaSolar>()

        init {
            arregloPlanetas
                .add(
                    Planeta(1, "Mercurio", "El primer planeta del Sistema Solar", 1, false)
                )
            arregloPlanetas
                .add(
                    Planeta(2, "Venus", "El planeta más caliente", 2, false)
                )
            arregloSistemasSolares
                .add(
                    SistemaSolar(1, "Sistema Solar", "Sistema Solar que contiene a la Tierra", 218)
                )
        }
    }
}