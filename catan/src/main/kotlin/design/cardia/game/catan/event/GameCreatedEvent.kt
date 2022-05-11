package design.cardia.game.catan.event

import design.cardia.game.catan.entity.Catan
import design.cardia.game.engine.event.Event
import java.util.UUID

class GameCreatedEvent(game: UUID, val players: List<UUID>) : Event<Catan>(game)
