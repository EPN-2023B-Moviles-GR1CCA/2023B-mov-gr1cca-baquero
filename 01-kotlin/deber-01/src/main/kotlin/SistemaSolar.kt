data class SistemaSolar(
    val planetas: MutableList<Planeta> = mutableListOf()
) {
    fun agregarPlaneta(planeta: Planeta) {
        planetas.add(planeta)
    }
}