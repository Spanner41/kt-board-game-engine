package design.cardia.game.catan.game.usecase

import design.cardia.game.catan.repository.IReadRepository
import design.cardia.game.engine.event.Game
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetGame(
    private val readRepository: IReadRepository
) {
    operator fun invoke(gameId: UUID): Game? {
        return readRepository.getGameById(gameId)
    }
}
