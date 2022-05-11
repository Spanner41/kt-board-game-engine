package design.cardia.game.engine.event

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.reflect.KClass

@Service
class InMemoryEventRegistry<G : Game> : EventRegistry<G> {
    private val registry = mutableMapOf<KClass<out Event<G>>, MutableList<(Event<G>) -> Unit>>()
    private val allRegistry = mutableListOf<(Event<G>) -> Unit>()

    override fun <E : Event<G>> registerAll(handler: (E) -> Unit): (E) -> Unit {
        allRegistry.add(handler as (Event<G>) -> Unit)
        return handler
    }

    inline fun <reified T : Event<G>> register(noinline handler: (T) -> Unit): (T) -> Unit {
        return register(T::class, handler)
    }

    override fun <T : Event<G>> register(clazz: KClass<out T>, handler: (T) -> Unit): (T) -> Unit {
        val list = registry.getOrPut(clazz) { mutableListOf() }
        list.add(handler as (Event<G>) -> Unit)
        return handler
    }

    override fun <T : Event<G>> deRegister(handler: (T) -> Unit) {
        registry.values.forEach { it.remove(handler as (Event<G>) -> Unit) }
    }

    override fun notify(event: Event<G>) {
        allRegistry.plus(registry[event::class] ?: emptyList())
            .also { logger.info("notifying ${it.size} listeners: $event") }
            .forEach { it(event) }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(InMemoryEventQueue::class.java)
    }
}
