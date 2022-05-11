package design.cardia.game.catan.event

import design.cardia.game.catan.entity.Catan
import design.cardia.game.engine.event.Event
import java.util.UUID

class ResourcesChangedEvent(
    game: UUID,
    val player: UUID,
    val resourceChanges: List<ResourceChange>,
    val reason: String
) : Event<Catan>(game)

data class ResourceChange(val type: String, val amount: Int)
