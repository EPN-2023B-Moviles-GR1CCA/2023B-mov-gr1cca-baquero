import java.util.Date

data class Planeta(
    var nombre: String,
    var posicion: Int,
    var fechaDescubrimiento: Date,
    var diametro: Double,
    var distanciaDelSol: Double,
    var duracionDia: Double,
    var perteneceASistemaSolar: Boolean,
    var tieneVida: Boolean,
    var numeroSatelites: Int
)