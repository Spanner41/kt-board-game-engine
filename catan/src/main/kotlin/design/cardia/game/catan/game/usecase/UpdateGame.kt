package design.cardia.game.catan.game.usecase

import design.cardia.game.engine.event.EventNotSupportedException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UpdateGame(
    private val placeRoad: PlaceRoad
) {
    operator fun invoke(game: UUID, player: UUID, action: String, attributes: Map<String, String>) {
        when (action) {
            "build-road" -> placeRoad(game, player, UUID.fromString(attributes["edge"]))
            else -> throw EventNotSupportedException("Invalid action: $action")
        }
    }
}
