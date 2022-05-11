package design.cardia.game.server.model

import java.util.UUID

data class GameUpdateRequest(
    val game: UUID,
    val player: UUID,
    val action: String,
    val attributes: Map<String, String>
)
