package design.cardia.game.catan.event

import design.cardia.game.catan.entity.Catan
import design.cardia.game.engine.event.Event
import java.util.UUID

class ControlPassedEvent(game: UUID, val player: UUID, val nextPlayer: UUID) : Event<Catan>(game)
