package design.cardia.game.catan.entity

import java.util.UUID

data class Board(
    val tiles: List<Tile>,
    val corners: List<Corner>,
    val edges: List<Edge>,
    val chits: List<Chit>,
    val placedRoads: List<UUID>,
    val placedSettlements: List<UUID>,
    val placedCities: List<UUID>,
    val robber: Robber
)
