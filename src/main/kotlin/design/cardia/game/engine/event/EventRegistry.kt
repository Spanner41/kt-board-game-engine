package design.cardia.game.engine.event

import kotlin.reflect.KClass

interface EventRegistry<G : Game> {
    fun notify(event: Event<G>)
    fun <E : Event<G>> registerAll(handler: (E) -> Unit): (E) -> Unit
    fun <E : Event<G>> register(clazz: KClass<out E>, handler: (E) -> Unit): (E) -> Unit
    fun <E : Event<G>> deRegister(handler: (E) -> Unit)
}
