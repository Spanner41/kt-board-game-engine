package design.cardia.game.catan.processor

import design.cardia.game.catan.builder.CatanBuilder
import design.cardia.game.catan.entity.Building
import design.cardia.game.catan.entity.Catan
import design.cardia.game.catan.event.CardDrawnEvent
import design.cardia.game.catan.event.ControlPassedEvent
import design.cardia.game.catan.event.GameCreatedEvent
import design.cardia.game.catan.event.RoadBuiltEvent
import design.cardia.game.catan.event.SettlementBuiltEvent
import design.cardia.game.catan.repository.IReadRepository
import design.cardia.game.catan.repository.IWriteRepository
import design.cardia.game.engine.event.Event
import design.cardia.game.engine.event.EventProcessor
import java.util.UUID

class CatanEventProcessor(
    private val writeRepository: IWriteRepository,
    private val readRepository: IReadRepository,
    private val catanBuilder: CatanBuilder
) : EventProcessor<Catan> {
    override fun process(events: List<Event<Catan>>) {
        events.forEach { event ->
            writeRepository.addEvent(event)
            when (event) {
                is GameCreatedEvent -> process(event)
                is SettlementBuiltEvent -> process(event)
                is RoadBuiltEvent -> process(event)
                is CardDrawnEvent -> process(event)
                is ControlPassedEvent -> process(event)
            }
        }
    }

    private fun process(event: GameCreatedEvent) {
        val game = catanBuilder.createGame(event)
        readRepository.addGame(game)
    }

    private fun process(event: SettlementBuiltEvent) {
        val game = getCatanGame(event.gameId) ?: return
        game.players[event.player]?.placedSettlements?.add(event.corner)
        game.board.corners.values
            .firstOrNull { it.id == event.corner }
            ?.content = Building(event.player, "Settlement")
    }

    private fun process(event: RoadBuiltEvent) {
        val game = getCatanGame(event.gameId) ?: return
        game.players[event.player]?.placedRoads?.add(event.edge)
        game.board.edges.values
            .firstOrNull { it.id == event.edge }
            ?.let {
                if (!it.hasRoad) {
                    it.player = event.player
                    it.hasRoad = true
                }
            }
    }

    private fun process(event: CardDrawnEvent) {
        val game = getCatanGame(event.gameId) ?: return
        game.developmentDeck.minus(event.card)
        game.players[event.player]?.cards?.plus(event.card)
    }

    private fun process(event: ControlPassedEvent) {
        val game = getCatanGame(event.gameId) ?: return
        game.currentPlayer = event.nextPlayer
    }

    private fun getCatanGame(gameId: UUID): Catan? {
        val game = readRepository.getGameById(gameId)
        return if (game is Catan) game else null
    }
}
