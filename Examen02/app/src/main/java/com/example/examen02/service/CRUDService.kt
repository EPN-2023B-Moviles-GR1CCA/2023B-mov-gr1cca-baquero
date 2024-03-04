package com.example.examen02.service

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.examen02.models.Planeta
import com.example.examen02.models.SistemaSolar
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class CRUDService(private val context: Context) {
    private val db: FirebaseFirestore = Firebase.firestore

    fun addSistema(sistema: SistemaSolar) {
        db.collection("sistemas").add(sistema)
    }

    suspend fun leerSistemas(): List<SistemaSolar> {
        return try {
            db.collection("sistemas").get().await().documents.mapNotNull { snapshot ->
                snapshot.data?.let { data ->
                    SistemaSolar(
                        nombre = data["nombre"] as? String ?: "",
                        descripcion = data["descripcion"] as? String ?: "",
                        cantidadSatelites = data["cantidad_satelites"] as? Int ?: 0,
                        planetas = mutableListOf()
                    )
                }
            }
        } catch (e: Exception) {
            listOf()
        }
    }

    fun actualizarSistema(nombre: String, nuevoSistema: SistemaSolar) {
        db.collection("sistemas").whereEqualTo("nombre", nombre).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("sistemas").document(document.id).set(nuevoSistema)
                }
            }
    }

    fun eliminarSistema(nombre: String) {
        db.collection("sistemas").whereEqualTo("nombre", nombre).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("sistemas").document(document.id).delete()
                }
            }
    }

    fun addPlaneta(planeta: Planeta) {
        db.collection("planetas").add(planeta)
    }

    suspend fun leerPlanetas(): List<Planeta> {
        return try {
            db.collection("planetas").get().await().documents.mapNotNull { snapshot ->
                snapshot.data?.let { data ->
                    val nombre = data["nombre"] as? String ?: ""
                    val descripcion = data["descripcion"] as? String ?: ""
                    val posicion = data["posicion"] as? Int ?: 0
                    val tieneVida = data["tiene_vida"] as? Boolean ?: false

                    Planeta(nombre, descripcion, posicion, tieneVida)
                }
            }
        } catch (e: Exception) {
            listOf()
        }
    }

    fun actualizarPlaneta(nombre: String, nuevoPlaneta: Planeta) {
        db.collection("planetas").whereEqualTo("nombre", nombre).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("planetas").document(document.id).set(nuevoPlaneta)
                }
            }
    }

    fun eliminarPlaneta(nombre: String) {
        db.collection("planetas").whereEqualTo("nombre", nombre).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("planetas").document(document.id).delete()
                }
            }
    }

}