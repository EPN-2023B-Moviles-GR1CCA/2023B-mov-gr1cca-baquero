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

    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00)
    calcularSueldo(10.00, 12.00, 20.00)
    //Parametros nombrados
    calcularSueldo(sueldo = 10.00)
    calcularSueldo(sueldo = 10.00, bonoEspecial = 15.00)
    calcularSueldo(sueldo = 10.00, bonoEspecial = 12.00, tasa = 20.00)

    calcularSueldo(10.00, bonoEspecial = 20.00) //Named Parameters
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) //Parámetros nombrados

    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) //Parámetros nombrados

    val sumaUno = Suma(1, 1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)

}

//Clases
abstract class Numeros(
    protected  val numeroUno: Int,
    protected val numeroDos: Int,
){
    init {
        this.numeroUno; this.numeroDos;
        numeroUno; numeroDos;
        println("Inicializando")

    }
}

class Suma(
    uno: Int,
    dos: Int,
): Numeros(uno, dos) {
    init {
        this.numeroUno; numeroUno;
        this.numeroDos; numeroDos;
    }
    constructor(
        uno: Int?,
        dos: Int
    ) : this (
        if (uno == null) 0 else uno,
        dos
    ) {
        numeroUno
    }

    constructor(
        uno: Int,
        dos: Int?,
    ) : this(
        uno,
        if (dos == null ) 0 else uno
    )
}

//Funciones
fun imprimirNombre(nombre: String): Unit {
    println("Nombre : ${nombre}")
}

fun calcularSueldo(
    sueldo: Double,
    tasa: Double = 12.00,
    bonoEspecial: Double? = null,
): Double {
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    } else {
        bonoEspecial.dec()
        return sueldo * (100/tasa) + bonoEspecial
    }
}



