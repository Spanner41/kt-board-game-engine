package design.cardia.game.engine.event

import java.time.Instant
import java.util.UUID

abstract class Event<G : Game>(
    val gameId: UUID
) {
    val id: UUID = UUID.randomUUID()
    val createdAt: Instant = Instant.now()
}

abstract class Game(
    val id: UUID
)

class EventNotSupportedException(message: String? = null, cause: Throwable? = null) : RuntimeException(message, cause)
