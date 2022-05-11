package design.cardia.game.engine.event

import org.slf4j.LoggerFactory

class EventConsumer<G : Game>(
    private val dequeue: EventDequeue<G>,
    private val registry: EventRegistry<G>
) {
    fun runWhile(condition: () -> Boolean = { false }) {
        while (condition()) {
            logger.info("polling queue")
            val event = dequeue.poll()
            event?.let {
                registry.notify(it)
            }
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(EventConsumer::class.java)
    }
}
