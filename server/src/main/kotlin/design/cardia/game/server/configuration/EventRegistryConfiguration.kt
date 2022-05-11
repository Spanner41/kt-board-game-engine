package design.cardia.game.server.configuration

import design.cardia.game.catan.entity.Catan
import design.cardia.game.catan.processor.CatanEventProcessor
import design.cardia.game.engine.event.Event
import design.cardia.game.engine.event.EventConsumer
import design.cardia.game.engine.event.EventRegistry
import org.slf4j.LoggerFactory
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
open class EventRegistryConfiguration(
    private val eventRegistry: EventRegistry<Catan>,
    private val eventProcessor: CatanEventProcessor,
    private val eventConsumer: EventConsumer<Catan>
) {
    @EventListener(ContextRefreshedEvent::class)
    @Async
    open fun setup() {
        logger.info("Initializing event registry")
        eventRegistry.registerAll<Event<Catan>> { eventProcessor.process(listOf(it)) }
        eventConsumer.runWhile { true }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(EventRegistryConfiguration::class.java)
    }
}
