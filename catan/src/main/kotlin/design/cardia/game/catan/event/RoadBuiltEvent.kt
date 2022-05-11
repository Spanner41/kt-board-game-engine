package design.cardia.game.catan.event

import design.cardia.game.catan.entity.Catan
import design.cardia.game.engine.event.Event
import java.util.UUID

class RoadBuiltEvent(game: UUID, val player: UUID, val edge: UUID) : Event<Catan>(game)
