package com.example.deber02

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqlitePlanetaHelper(
    contexto: Context?, //This
) : SQLiteOpenHelper(
    contexto,
    "deber02", //nombre BDD
    null,
    5
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaPlaneta =
            """
                CREATE TABLE PLANETA(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT,
                descripcion TEXT,
                posicion INTEGER,
                tiene_vida INTEGER
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaPlaneta)

        val scriptCrearTablaSistemaSolar =
            """
                CREATE TABLE SISTEMASOLAR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT,
                descripcion TEXT,
                cantidad_satelites INTEGER,
                id_planeta INTEGER,
                FOREIGN KEY (id_planeta) REFERENCES PLANETA(id)
                )
            """.trimIndent()
        db?.execSQL(scriptCrearTablaSistemaSolar)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 5) {
            // Elimina las tablas si existen
            db?.execSQL("DROP TABLE IF EXISTS SISTEMASOLAR")
            db?.execSQL("DROP TABLE IF EXISTS PLANETA")

            // Vuelve a crear la tabla PLANETA
            val scriptSQLCrearTablaPlaneta =
                """
                CREATE TABLE PLANETA(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT,
                descripcion TEXT,
                posicion INTEGER,
                tiene_vida INTEGER
                )
            """.trimIndent()
            db?.execSQL(scriptSQLCrearTablaPlaneta)

            // Vuelve a crear la tabla SISTEMASOLAR con ON DELETE CASCADE
            val scriptCrearTablaSistemaSolar =
                """
                CREATE TABLE SISTEMASOLAR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT,
                descripcion TEXT,
                cantidad_satelites INTEGER,
                id_planeta INTEGER,
                FOREIGN KEY (id_planeta) REFERENCES PLANETA(id)
                )
            """.trimIndent()
            db?.execSQL(scriptCrearTablaSistemaSolar)
        }
    }

    // CRUD Planeta

    fun crearPlaneta(nuevoPlaneta: Planeta): Boolean {
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()

        valoresAGuardar.put("nombre", nuevoPlaneta.nombre)
        valoresAGuardar.put("descripcion", nuevoPlaneta.descripcion)
        valoresAGuardar.put("posicion", nuevoPlaneta.posicion)
        valoresAGuardar.put("tiene_vida", if (nuevoPlaneta.tieneVida) 1 else 0)

        val resultadoAGuardar = baseDatosEscritura
            .insert(
                "PLANETA",
                null,
                valoresAGuardar
            )

        baseDatosEscritura.close()
        return resultadoAGuardar != -1L
    }

    fun obtenerPlanetas(): ArrayList<Planeta> {
        val baseDatosLectura = readableDatabase

        val scriptConsultaLectura =
            """
                SELECT * FROM PLANETA
            """.trimIndent()

        val resultadoConsulta = baseDatosLectura.rawQuery(scriptConsultaLectura, null)

        val planetas = arrayListOf<Planeta>()

        if (resultadoConsulta != null && resultadoConsulta.moveToFirst()) {

            do {
                val id = resultadoConsulta.getInt(0)
                val nombre = resultadoConsulta.getString(1)
                val descripcion = resultadoConsulta.getString(2)
                val posicion = resultadoConsulta.getInt(3)
                val tieneVida = resultadoConsulta.getInt(4)

                val tieneVidaBool = if (tieneVida == 1) true else false


                if (nombre != null) {
                    val planetaEncontrado = Planeta(
                        id, nombre, descripcion, posicion, tieneVidaBool
                    )

                    planetas.add(planetaEncontrado)
                }
            } while (resultadoConsulta.moveToNext())
        }

        resultadoConsulta?.close()
        baseDatosLectura.close()
        return planetas
    }

    fun consultarPlanetaporId(id: Int): Planeta {
        val baseDatosLectura = readableDatabase

        val scriptConsultaLectura = """
            SELECT * FROM PLANETA WHERE ID = ?
        """.trimIndent()

        val parametrosConsultaLectura = arrayOf(id.toString())

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaLectura
        )

        val existePlaneta = resultadoConsultaLectura.moveToFirst()

        val planetaEncontrado = Planeta(1, "planeta", "descripcion", 1, true)
        if (existePlaneta) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val descripcion = resultadoConsultaLectura.getString(2)
                val posicion = resultadoConsultaLectura.getInt(3)
                val tieneVida = resultadoConsultaLectura.getInt(4)
                val tieneVidaBool = if (tieneVida == 1) true else false

                if (nombre != null) {
                    planetaEncontrado.id = id
                    planetaEncontrado.nombre = nombre
                    planetaEncontrado.descripcion = descripcion
                    planetaEncontrado.posicion = posicion
                    planetaEncontrado.tieneVida = tieneVidaBool
                }
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return planetaEncontrado
    }

    fun actualizarPlanetaPorId(
        datosActualizados: Planeta
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", datosActualizados.nombre)
        valoresAActualizar.put("descripcion", datosActualizados.descripcion)
        valoresAActualizar.put("posicion", datosActualizados.posicion)
        valoresAActualizar.put("tiene_vida", datosActualizados.tieneVida)

        val parametrosConsultaActualizar = arrayOf(datosActualizados.id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "PLANETA",
                valoresAActualizar,
                "id = ?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return resultadoActualizacion != -1
    }

    fun eliminarPlanetaPorId(id: Int): Boolean {
        val conexionEscritura = writableDatabase

        val parametrosConsultaDelete = arrayOf(id.toString())

        val resultadoEliminacion = conexionEscritura
            .delete(
                "PLANETA",
                "id = ?",
                parametrosConsultaDelete
            )

        conexionEscritura.close()
        return resultadoEliminacion != -1
    }
}

class SqliteSistemaSolarHelper(
    contexto: Context?,
) : SQLiteOpenHelper(
    contexto,
    "deber02",
    null,
    5
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaSistemaSolar =
            """
                CREATE TABLE SISTEMASOLAR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT,
                descripcion TEXT,
                cantidad_satelites INTEGER,
                id_planeta INTEGER,
                FOREIGN KEY (id_planeta) REFERENCES PLANETA(id)
                )
            """.trimIndent()
        db?.execSQL(scriptCrearTablaSistemaSolar)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 5) {
            db?.execSQL("DROP TABLE IF EXISTS SISTEMASOLAR")

            val scriptCrearTablaSistemaSolar =
                """
                CREATE TABLE SISTEMASOLAR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT,
                descripcion TEXT,
                cantidad_satelites INTEGER,
                id_planeta INTEGER,
                FOREIGN KEY (id_planeta) REFERENCES PLANETA(id)
                )
            """.trimIndent()
            db?.execSQL(scriptCrearTablaSistemaSolar)
        }
    }

    // CRUD Sistema Solar

    fun crearSistemaSolar(nuevoSistemaSolar: SistemaSolar): Boolean {
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()

        valoresAGuardar.put("nombre", nuevoSistemaSolar.nombre)
        valoresAGuardar.put("descripcion", nuevoSistemaSolar.descripcion)
        valoresAGuardar.put("cantidad_satelites", nuevoSistemaSolar.cantidadSatelites)
        valoresAGuardar.put("id_planeta", nuevoSistemaSolar.planeta.id)

        val resultadoAGuardar = baseDatosEscritura
            .insert(
                "SISTEMASOLAR",
                null,
                valoresAGuardar
            )

        baseDatosEscritura.close()
        return resultadoAGuardar != -1L
    }

    fun obtenerSistemasSolares(): ArrayList<SistemaSolar> {
        val baseDatosLectura = readableDatabase

        val scriptConsultaLectura = """
            SELECT * FROM SISTEMASOLAR
        """.trimIndent()

        val resultadoConsulta = baseDatosLectura.rawQuery(scriptConsultaLectura, null)

        val sistemasSolares = arrayListOf<SistemaSolar>()

        if (resultadoConsulta != null && resultadoConsulta.moveToFirst()) {

            do {
                val id = resultadoConsulta.getInt(0)
                val nombre = resultadoConsulta.getString(1)
                val descripcion = resultadoConsulta.getString(2)
                val cantidadSatelites = resultadoConsulta.getInt(3)
                val idPlaneta = resultadoConsulta.getInt(4)

                val planeta = SqlitePlanetaHelper(null).consultarPlanetaporId(idPlaneta)

                if (nombre != null) {
                    val sistemaEncontrado = SistemaSolar(
                        id, nombre, descripcion, cantidadSatelites, planeta
                    )

                    sistemasSolares.add(sistemaEncontrado)
                }
            } while (resultadoConsulta.moveToNext())
        }

        resultadoConsulta?.close()
        baseDatosLectura.close()
        return sistemasSolares
    }

    fun consultarSistemaPorId(id: Int): SistemaSolar {
        val baseDatosLectura = readableDatabase

        val scriptConsultaLectura = """
            SELECT * FROM ALBUM WHERE ID = ?
        """.trimIndent()

        val parametrosConsultaLectura = arrayOf(id.toString())

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaLectura
        )

        val existeSistema = resultadoConsultaLectura.moveToFirst()

        val sistemaEncontrado = SistemaSolar(
            1,
            "Sistema Solar",
            "Descripcion",
            0,
            Planeta(1, "Planeta", "Descripcion", 1, true)
        )

        if (existeSistema) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val descripcion = resultadoConsultaLectura.getString(2)
                val cantidadSatelites = resultadoConsultaLectura.getInt(3)
                val planetaId = resultadoConsultaLectura.getInt(4)

                val planeta = SqlitePlanetaHelper(null).consultarPlanetaporId(planetaId)

                if (nombre != null) {
                    sistemaEncontrado.id = id
                    sistemaEncontrado.nombre = nombre
                    sistemaEncontrado.descripcion = descripcion
                    sistemaEncontrado.cantidadSatelites = cantidadSatelites
                    sistemaEncontrado.planeta = planeta
                }
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return sistemaEncontrado
    }

    fun actualizarSistemaPorId(
        datosActualizados: SistemaSolar
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()

        valoresAActualizar.put("nombre", datosActualizados.nombre)
        valoresAActualizar.put("descripcion", datosActualizados.descripcion)
        valoresAActualizar.put("cantidad_satelites", datosActualizados.cantidadSatelites)
        valoresAActualizar.put("id_planeta", datosActualizados.planeta.id)

        val parametrosConsultaActualizar = arrayOf(datosActualizados.id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "SISTEMASOLAR",
                valoresAActualizar,
                "id = ?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return resultadoActualizacion != -1
    }

    fun eliminarSistemaPorId(id: Int): Boolean {
        val conexionEscritura = writableDatabase

        val parametrosConsultaDelete = arrayOf(id.toString())

        val resultadoEliminacion = conexionEscritura
            .delete(
                "SISTEMASOLAR",
                "id = ?",
                parametrosConsultaDelete
            )

        conexionEscritura.close()
        return resultadoEliminacion != -1
    }
}