package design.cardia.game.catan.entity

import java.util.UUID

data class Player(
    val id: UUID,
    val cards: MutableList<DevelopmentCard>,
    val resources: MutableMap<String, Int> = mutableMapOf(),
    val placedRoads: MutableList<UUID>,
    val placedCities: MutableList<UUID>,
    var placedSettlements: MutableList<UUID>,
    val longestRoad: Boolean,
    val largestArmy: Boolean,
) {
    val victoryPoints: Int
        get() =
            cards.count { it == DevelopmentCard.VICTORY_POINT } +
                (placedCities.size * 2) +
                (placedSettlements.size) +
                (if (longestRoad) 2 else 0) +
                (if (largestArmy) 2 else 0)
}
