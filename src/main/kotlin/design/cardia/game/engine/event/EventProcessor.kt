package design.cardia.game.engine.event

interface EventProcessor<G : Game> {
    fun process(events: List<Event<G>>)
}
