package design.cardia.game.catan.repository

import design.cardia.game.engine.event.Event

interface IWriteRepository {
    fun addEvent(event: Event<*>)
}
