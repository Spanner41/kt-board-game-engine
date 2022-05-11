package design.cardia.game.server.repository

import design.cardia.game.catan.repository.IWriteRepository
import design.cardia.game.engine.event.Event
import org.springframework.stereotype.Repository

@Repository
class WriteRepository(
    private val events: MutableList<Event<*>> = mutableListOf()
) : IWriteRepository {
    override fun addEvent(event: Event<*>) {
        events.add(event)
    }
}
