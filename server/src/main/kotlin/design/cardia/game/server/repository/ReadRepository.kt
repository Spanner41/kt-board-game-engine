package design.cardia.game.server.repository

import design.cardia.game.catan.entity.Catan
import design.cardia.game.catan.repository.IReadRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class ReadRepository(
    private val games: MutableMap<UUID, Catan> = mutableMapOf()
) : IReadRepository {
    override fun addGame(game: Catan) {
        games[game.id] = game
    }

    override fun getGameById(id: UUID) = games[id]

    override fun getPlayerById(gameId: UUID, playerId: UUID) = games[gameId]?.players?.firstOrNull { it.id == playerId }

    override fun getCornerById(gameId: UUID, cornerId: UUID) = games[gameId]?.board?.corners?.firstOrNull { it.id == cornerId }

    override fun getEdgeById(gameId: UUID, edgeId: UUID) = games[gameId]?.board?.edges?.firstOrNull { it.id == edgeId }

    override fun getTileById(gameId: UUID, tileId: UUID) = games[gameId]?.board?.tiles?.firstOrNull { it.id == tileId }
}
