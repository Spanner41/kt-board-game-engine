package design.cardia.game.catan.repository

import design.cardia.game.catan.entity.Catan
import design.cardia.game.catan.entity.Corner
import design.cardia.game.catan.entity.Edge
import design.cardia.game.catan.entity.Player
import design.cardia.game.catan.entity.Tile
import java.util.UUID

interface IReadRepository {
    fun addGame(game: Catan)

    fun getGameById(id: UUID): Catan?

    fun getPlayerById(gameId: UUID, playerId: UUID): Player?

    fun getCornerById(gameId: UUID, cornerId: UUID): Corner?

    fun getEdgeById(gameId: UUID, edgeId: UUID): Edge?

    fun getTileById(gameId: UUID, tileId: UUID): Tile?
}
