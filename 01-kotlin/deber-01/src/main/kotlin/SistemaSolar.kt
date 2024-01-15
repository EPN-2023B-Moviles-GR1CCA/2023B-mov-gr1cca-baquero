data class SistemaSolar(
    val nombre: String,
    var planetas: MutableList<Planeta> = mutableListOf()
) {
    fun agregarPlaneta(planeta: Planeta) {
        planetas.add(planeta)
    }
}