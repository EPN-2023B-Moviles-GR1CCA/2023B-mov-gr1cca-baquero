import com.google.gson.Gson
import java.io.File
import java.text.SimpleDateFormat
import java.util.Scanner

fun main() {
    val jsonFileName = "sistemaSolar.json"
    val jsonFile = File(jsonFileName)
    val jsonString = jsonFile.readText()
    val gson = Gson()
    val sistemaSolar: SistemaSolar = gson.fromJson(jsonString, SistemaSolar::class.java)

    val scanner = Scanner(System.`in`)

    loop@ while (true) {
        println("\n---Bienvenido al programa---")
        println("Elige una opción")
        println("1. Ver planetas del Sistema Solar")
        println("2. Agregar un planeta")
        println("3. Editar un planeta")
        println("4. Eliminar un planeta")
        println("5. Guardar y salir")

        when (scanner.nextInt()) {
            1 -> {
                verInformacionSistemaSolar(sistemaSolar)
            }

            2 -> {
                crearPlaneta(sistemaSolar, scanner)
            }

            3 -> {
                editarPlaneta(sistemaSolar, scanner)
            }

            4 -> {
                eliminarPlaneta(sistemaSolar, scanner)
            }

            5 -> {
                guardarArchivo(jsonFileName, sistemaSolar.planetas, gson)
                break@loop
            }

            else -> println("Opcióm inválida. Por favor intenta nuevamente")
        }
    }

    println("Gracias por utilizar el programa!")
}

fun listarPlanetas(sistemaSolar: SistemaSolar) {
    println("\n---Planetas en el ${sistemaSolar.nombre}---")
    sistemaSolar.planetas.forEachIndexed { index, planeta ->
        println("${index}. ${planeta.nombre}")
    }
}

//CRUD
//CREATE
fun crearPlaneta(sistemaSolar: SistemaSolar, scanner: Scanner) {
    println("\n---Creación de un planeta---")
    println("Nombre del planeta: ")
    val nombre = scanner.next()
    println("Posicion en el sistema solar: ")
    val posicion = scanner.nextInt()
    println("Fecha de descubrimiento (yyyy-mm-dd): ")
    val fechaDescubrimientoStr = scanner.next()
    val fechaDescubrimiento = SimpleDateFormat("yyyy-MM-dd").parse(fechaDescubrimientoStr)
    println("Diámetro (km): ")
    val diametro = scanner.nextDouble()
    println("Distancia al sol (km): ")
    val distanciaAlSol = scanner.nextDouble()
    println("Duración del día en horas: ")
    val duracionDia = scanner.nextDouble()
    println("Pertenece al Sistema Solar? (Y/n): ")
    val perteneceASistemaSolar = if (scanner.next() == "y" || scanner.next() == "Y") true else false
    println("El planeta tiene vida? (Y/n): ")
    val tieneVida = if (scanner.next() == "y" || scanner.next() == "Y") true else false
    println("Número de satélites")
    val numeroSatelites = scanner.nextInt()

    val nuevoPlaneta = Planeta(
        nombre,
        posicion,
        fechaDescubrimiento,
        diametro,
        distanciaAlSol,
        duracionDia,
        perteneceASistemaSolar,
        tieneVida,
        numeroSatelites
    )

    sistemaSolar.agregarPlaneta(nuevoPlaneta)
    println("Planeta creado con éxito!")
}

//READ
fun verInformacionSistemaSolar(sistemaSolar: SistemaSolar) {
    println("\n---Planetas en el ${sistemaSolar.nombre}---")
    sistemaSolar.planetas.forEachIndexed { index, planeta ->
        println("---${index}. ${planeta.nombre}---")
        println("Posición en el ${sistemaSolar.nombre}: ${planeta.posicion}")
        println("Fecha de descubrimiento: ${planeta.fechaDescubrimiento}")
        println("Diámetro: ${planeta.diametro} km")
        println("Se encuentra a ${planeta.distanciaDelSol} km del sol")
        println("El día dura ${planeta.duracionDia} horas")
        if (planeta.perteneceASistemaSolar)
            println("Sí pertenece al Sistema Solar")
        else
            println("No pertence al Sistema Solar")
        if (planeta.tieneVida)
            println("El planeta sí tiene vida")
        else
            println("El planeta no tiene vida")
        println("Tiene ${planeta.numeroSatelites} satélites")
    }
}

//UPDATE
fun editarPlaneta(sistemaSolar: SistemaSolar, scanner: Scanner) {
    listarPlanetas(sistemaSolar)
    println("Ingresa el indice del planeta a editar: ")
    val indicePlaneta = scanner.nextInt()
    println("\n---Edición de un planeta---")
    println("Nuevo nombre del planeta:")
    val nombre = scanner.next()
    println("Posicion en el sistema solar: ")
    val posicion = scanner.nextInt()
    println("Fecha de descubrimiento (yyyy-mm-dd): ")
    val fechaDescubrimientoStr = scanner.next()
    val fechaDescubrimiento = SimpleDateFormat("yyyy-MM-dd").parse(fechaDescubrimientoStr)
    println("Diámetro (km): ")
    val diametro = scanner.nextDouble()
    println("Distancia al sol (km): ")
    val distanciaAlSol = scanner.nextDouble()
    println("Duración del día en horas: ")
    val duracionDia = scanner.nextDouble()
    println("Pertence al Sistema Solar? (Y/n): ")
    val perteneceASistemaSolar = if (scanner.next() == "y" || scanner.next() == "Y") true else false
    println("El planeta tiene vida? (Y/n): ")
    val tieneVida = if (scanner.next() == "y" || scanner.next() == "Y") true else false
    println("Número de satélites")
    val numeroSatelites = scanner.nextInt()

    val planetaEditado = Planeta(
        nombre,
        posicion,
        fechaDescubrimiento,
        diametro,
        distanciaAlSol,
        duracionDia,
        perteneceASistemaSolar,
        tieneVida,
        numeroSatelites
    )

    sistemaSolar.planetas[indicePlaneta] = planetaEditado
    println("Planeta Editado con éxito!")
}

//DELETE
fun eliminarPlaneta(sistemaSolar: SistemaSolar, scanner: Scanner) {
    println("\n---Eliminar un planeta---")
    listarPlanetas(sistemaSolar)
    println("Ingresa el indice del planeta a eliminar: ")
    val indicePlaneta = scanner.nextInt()
    println("Estás seguro de eliminar el planeta ${indicePlaneta} (Y/n): ")
    if (scanner.next() == "y" || scanner.next() == "Y") {
        sistemaSolar.planetas.removeAt(indicePlaneta)
        println("Planeta eliminado con éxito!")
    } else
        println("Operación cancelada")

}

//Guardar archivo
fun <T> guardarArchivo(fileName: String, data: List<T>, gson: Gson) {
    val json = gson.toJson(data)
    File(fileName).writeText(json)
}