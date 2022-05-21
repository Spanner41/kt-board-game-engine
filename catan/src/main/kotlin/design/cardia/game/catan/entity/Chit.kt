package design.cardia.game.catan.entity

import java.util.UUID

class Chit(
    val id: UUID,
    val value: Int,
    var tile: UUID? = null
) {
    init {
        require((2..12).contains(value))
        require(value != 7)
    }

    fun dots() = if (value > 7) 13 - value else value - 1
}
