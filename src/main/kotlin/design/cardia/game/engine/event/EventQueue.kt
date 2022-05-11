package design.cardia.game.engine.event

interface EventEnqueue<G : Game> {
    fun put(event: Event<G>)
}

interface EventDequeue<G : Game> {
    fun poll(): Event<G>?
}
