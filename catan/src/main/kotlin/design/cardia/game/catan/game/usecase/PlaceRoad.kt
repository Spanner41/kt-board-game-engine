package design.cardia.game.catan.game.usecase

import design.cardia.game.catan.entity.Catan
import design.cardia.game.catan.event.RoadBuiltEvent
import design.cardia.game.engine.event.EventEnqueue
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PlaceRoad(
    private val eventQueue: EventEnqueue<Catan>
) {
    operator fun invoke(game: UUID, player: UUID, edge: UUID) {
        val event = RoadBuiltEvent(game, player, edge)
        eventQueue.put(event)
    }
}
