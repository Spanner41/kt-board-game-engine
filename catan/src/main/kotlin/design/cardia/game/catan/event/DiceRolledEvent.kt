package design.cardia.game.catan.event

import design.cardia.game.catan.entity.Catan
import design.cardia.game.engine.event.Event
import java.util.UUID

class DiceRolledEvent(
    game: UUID,
    val player: UUID,
    val yellowDieValue: UInt,
    val redDieValue: UInt
) : Event<Catan>(game)
