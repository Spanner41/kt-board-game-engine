package design.cardia.game.catan.game.usecase

import design.cardia.game.catan.entity.Catan
import design.cardia.game.catan.event.SettlementBuiltEvent
import design.cardia.game.engine.event.EventEnqueue
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PlaceSettlement(
    private val eventQueue: EventEnqueue<Catan>
) {
    operator fun invoke(game: UUID, player: UUID, corner: UUID) {
        val event = SettlementBuiltEvent(game, player, corner)
        eventQueue.put(event)
    }
}
