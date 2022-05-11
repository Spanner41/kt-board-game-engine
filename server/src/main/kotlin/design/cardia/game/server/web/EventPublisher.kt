package design.cardia.game.server.web

import design.cardia.game.engine.event.Event
import design.cardia.game.engine.event.EventProcessor
import design.cardia.game.engine.event.Game
import org.springframework.stereotype.Component

@Component
class EventPublisher(
    // private val eventBus: EventBus
) : EventProcessor<Game> {
    override fun process(events: List<Event<Game>>) {
        // events.forEach { eventBus.publish(it) }
    }
}

interface EventBus {
    fun publish(event: Event<Game>)
}
