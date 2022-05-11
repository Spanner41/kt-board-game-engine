package design.cardia.game.catan.event

import design.cardia.game.catan.entity.Catan
import design.cardia.game.engine.event.Event
import java.util.UUID

class RobberRolledEvent(
    game: UUID,
    val player: UUID,
    val targetTile: UUID,
    val previousTile: UUID,
    val robberTarget: UUID?
) : Event<Catan>(game)
