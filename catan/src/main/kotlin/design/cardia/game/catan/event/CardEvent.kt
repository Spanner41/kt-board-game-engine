package design.cardia.game.catan.event

import design.cardia.game.catan.entity.Catan
import design.cardia.game.catan.entity.DevelopmentCard
import design.cardia.game.catan.entity.Player
import design.cardia.game.catan.entity.ResourceType
import design.cardia.game.catan.entity.Tile
import design.cardia.game.engine.event.Event
import java.util.UUID

abstract class CardEvent(game: UUID, val player: UUID, val type: DevelopmentCard) : Event<Catan>(game)

class KnightCardPlayedEvent(
    game: UUID,
    player: UUID,
    val targetTile: Tile,
    val previousTile: Tile,
    val robberTarget: Player?
) : CardEvent(game, player, DevelopmentCard.KNIGHT)

class RoadBuildingCardPlayed(game: UUID, player: UUID) :
    CardEvent(game, player, DevelopmentCard.ROAD_BUILDING)

class MonopolyCardPlayed(game: UUID, player: UUID, val resourceType: ResourceType) :
    CardEvent(game, player, DevelopmentCard.MONOPOLY)

class YearOfPlentyCardPlayed(game: UUID, player: UUID, val resourceType: ResourceType) :
    CardEvent(game, player, DevelopmentCard.YEAR_OF_PLENTY)
