import java.util.*

fun main() {
    println("Ola Mundo")
    // val Inmutable
    val inmutable: String = "Nicolás";
    // inmutable = "Diego"
    // var Mutable
    var mutable: String = "Baquero"
    mutable = "Nicolás"

    var ejemploVariable = "Nicolás Baquero";
    val edadEjemplo: Int = 12
    ejemploVariable.trim();
    //ejemploVariable = edadEjemplo;

    //Vairable primitiva
    val nombreProfesor: String = "Adrián Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C';
    val mayorEdad: Boolean = true;

    //Clases Java
    val fechaNacimiento : Date = Date()

    // SWITCH
    val estadoCivilWhen = "C";
    when (estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }

    val coqueteo = if (estadoCivilWhen == "S") "Si" else "No"


}