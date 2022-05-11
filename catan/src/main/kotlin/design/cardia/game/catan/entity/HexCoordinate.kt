package design.cardia.game.catan.entity

data class HexCoordinate(val q: Int, val r: Int) {
    val s = -q - r

    override fun toString(): String {
        return "HexCoordinate(q=$q, r=$r, s=$s)"
    }
}
