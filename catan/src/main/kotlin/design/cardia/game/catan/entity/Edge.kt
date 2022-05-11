package design.cardia.game.catan.entity

import java.util.UUID

data class Edge(
    val id: UUID,
    val left: UUID,
    val right: UUID,
    var hasRoad: Boolean = false,
    var player: UUID? = null
)
