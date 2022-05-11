package design.cardia.game.catan.entity

import java.util.UUID

data class Tile(
    val id: UUID,
    val location: HexCoordinate,
    val type: ResourceType,
    val chit: UUID?
)
