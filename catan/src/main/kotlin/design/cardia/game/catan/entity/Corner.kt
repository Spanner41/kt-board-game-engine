package design.cardia.game.catan.entity

import java.util.UUID

data class Corner(
    var id: UUID,
    var location: HexCoordinate,
    var content: Building? = null,
    var port: Port? = null
)
