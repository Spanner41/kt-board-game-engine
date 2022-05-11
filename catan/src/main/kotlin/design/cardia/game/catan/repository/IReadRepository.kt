package design.cardia.game.catan.repository

import design.cardia.game.catan.entity.Catan
import java.util.UUID

interface IReadRepository {
    fun addGame(game: Catan)

    fun getGameById(id: UUID): Catan?
}
