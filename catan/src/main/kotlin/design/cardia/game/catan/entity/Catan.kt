package design.cardia.game.catan.entity

import design.cardia.game.engine.event.Game
import java.util.UUID

class Catan(
    id: UUID,
    var currentPlayer: UUID,
    var board: Board,
    var players: List<Player>,
    var yellowDie: UInt,
    var redDie: UInt,
    var developmentDeck: DevelopmentDeck
) : Game(id)
