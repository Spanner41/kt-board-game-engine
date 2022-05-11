package design.cardia.game.catan.player.usecase

import design.cardia.game.catan.entity.Catan
import design.cardia.game.catan.event.DiceRolledEvent
import design.cardia.game.engine.event.EventEnqueue
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RollDice(private val eventQueue: EventEnqueue<Catan>) {
    operator fun invoke(game: UUID, player: UUID): DiceRoll {
        val result = DiceRoll(roll(), roll())

        val event = DiceRolledEvent(game, player, result.yellowDieResult, result.redDieResult)
        eventQueue.put(event)

        return result
    }

    private fun roll() = (1u..6u).random()
}

class DiceRoll(val yellowDieResult: UInt, val redDieResult: UInt)
