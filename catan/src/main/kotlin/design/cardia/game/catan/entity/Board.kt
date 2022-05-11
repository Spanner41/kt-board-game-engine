package design.cardia.game.catan.entity

import java.util.UUID

data class Board(
    val tiles: Map<HexCoordinate, Tile>,
    val corners: Map<HexCoordinate, Corner>,
    val edges: Map<HexCoordinate, Edge>,
    val chits: Map<UUID, Chit>,
    val placedRoads: List<UUID>,
    val placedSettlements: List<UUID>,
    val placedCities: List<UUID>,
    val robber: Robber
)
