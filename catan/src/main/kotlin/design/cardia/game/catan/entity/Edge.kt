package design.cardia.game.catan.entity

import java.util.UUID

data class Edge(
    val id: UUID,
    var hasRoad: Boolean = false,
    var player: UUID? = null
)
