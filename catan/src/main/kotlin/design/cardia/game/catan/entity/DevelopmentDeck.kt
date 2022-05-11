package design.cardia.game.catan.entity

class DevelopmentDeck : Shuffler<DevelopmentCard>() {
    init {
        repeat(14) { add(DevelopmentCard.KNIGHT) }
        repeat(5) { add(DevelopmentCard.VICTORY_POINT) }
        repeat(2) { add(DevelopmentCard.MONOPOLY) }
        repeat(2) { add(DevelopmentCard.ROAD_BUILDING) }
        repeat(2) { add(DevelopmentCard.YEAR_OF_PLENTY) }
    }

    operator fun minus(card: DevelopmentCard): DevelopmentDeck = minus(card)
    operator fun plus(card: DevelopmentCard): DevelopmentDeck = plus(card)
}
