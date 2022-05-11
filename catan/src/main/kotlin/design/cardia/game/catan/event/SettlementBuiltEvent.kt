package design.cardia.game.catan.event

import design.cardia.game.catan.entity.Catan
import design.cardia.game.engine.event.Event
import java.util.UUID

class SettlementBuiltEvent(game: UUID, val player: UUID, val corner: UUID) : Event<Catan>(game)
