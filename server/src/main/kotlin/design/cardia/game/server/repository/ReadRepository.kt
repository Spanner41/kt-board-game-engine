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
}
