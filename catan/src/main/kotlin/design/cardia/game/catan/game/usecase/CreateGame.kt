package design.cardia.game.catan.game.usecase

import design.cardia.game.catan.entity.Catan
import design.cardia.game.catan.event.GameCreatedEvent
import design.cardia.game.engine.event.EventEnqueue
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CreateGame(
    private val eventQueue: EventEnqueue<Catan>
) {
    operator fun invoke(players: List<UUID>): UUID {
        val gameId = UUID.randomUUID()
        eventQueue.put(GameCreatedEvent(gameId, players))
        return gameId
    }
}
