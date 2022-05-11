package design.cardia.game.engine.event

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.concurrent.LinkedBlockingQueue

@Service
class InMemoryEventQueue<G : Game> : EventEnqueue<G>, EventDequeue<G> {
    private val queue: LinkedBlockingQueue<Event<G>> = LinkedBlockingQueue()

    override fun put(event: Event<G>) {
        logger.info("queueing event: $event")
        queue.offer(event)
    }

    override fun poll(): Event<G>? {
        return queue.take().also { logger.info("taking event: $it") }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(InMemoryEventQueue::class.java)
    }
}
