package design.cardia.game.catan.entity

data class HexCoordinate(val q: Int, val r: Int) {
    val s = -q - r

    operator fun times(c: Int) = HexCoordinate(q * c, r * c)

    operator fun plus(other: HexCoordinate) = HexCoordinate(q + other.q, r + other.r)

    override fun toString(): String {
        return "HexCoordinate(q=$q, r=$r, s=$s)"
    }
}
