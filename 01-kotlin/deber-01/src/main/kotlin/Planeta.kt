import java.util.Date

data class Planeta(
    val nombre: String,
    val posicion: Int,
    val fechaDescubrimiento: Date,
    val diametro: Double,
    val distanciaDelSol: Double,
    val duracionDia: Double,
    val perteneceASistemaSolar: Boolean,
    val tieneVida: Boolean,
    val numeroSatelites: Int
)