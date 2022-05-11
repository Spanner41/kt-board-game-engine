package design.cardia.game.server.model

import java.util.UUID

data class GameCreateRequest(
    val players: List<UUID>
)
