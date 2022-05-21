package design.cardia.game.catan.builder

import design.cardia.game.catan.entity.Board
import design.cardia.game.catan.entity.Catan
import design.cardia.game.catan.entity.Chit
import design.cardia.game.catan.entity.Corner
import design.cardia.game.catan.entity.DevelopmentDeck
import design.cardia.game.catan.entity.Edge
import design.cardia.game.catan.entity.HexCoordinate
import design.cardia.game.catan.entity.Player
import design.cardia.game.catan.entity.ResourceType
import design.cardia.game.catan.entity.Robber
import design.cardia.game.catan.entity.Shuffler
import design.cardia.game.catan.entity.Tile
import design.cardia.game.catan.event.GameCreatedEvent
import java.util.UUID

class CatanBuilder {
    fun createGame(event: GameCreatedEvent): Catan {
        return Catan(
            id = event.gameId,
            players = createPlayers(event),
            currentPlayer = event.players.first(),
            board = createBoard(event),
            yellowDie = 1u,
            redDie = 1u,
            developmentDeck = DevelopmentDeck()
        )
    }

    private fun createPlayers(event: GameCreatedEvent): List<Player> =
        event.players.map {
            Player(
                it,
                mutableListOf(),
                mutableMapOf(),
                mutableListOf(),
                mutableListOf(),
                mutableListOf(),
                longestRoad = false,
                largestArmy = false
            )
        }

    private fun createBoard(event: GameCreatedEvent): Board {
        val chits = createChits()
        val tiles = createTiles(chits)
        return Board(
            tiles = tiles,
            corners = createCorners(),
            edges = createEdges(),
            chits = chits,
            placedRoads = listOf(),
            placedSettlements = listOf(),
            placedCities = listOf(),
            robber = Robber(UUID.randomUUID())
        )
    }

    private fun createChits() = chitNumbers.map { Chit(UUID.randomUUID(), it, null) }

    private fun createTiles(chits: List<Chit>): List<Tile> {
        require(tileLocations.size - 1 == chits.count())

        val chitShuffler = Shuffler<Chit>().also { shuffler ->
            for (chit in chits) {
                shuffler.add(chit)
            }
        }

        val tileShuffler = Shuffler<ResourceType>().also { shuffler ->
            for ((type, count) in tileTypes) {
                for (i in 1..count) {
                    shuffler.add(type)
                }
            }
        }

        return tileLocations.map {
            val resource = tileShuffler.randomElement()!!
            val chit = if (resource != ResourceType.DESERT) chitShuffler.randomElement()!! else null
            val tile = Tile(
                UUID.randomUUID(),
                it,
                resource,
                chit?.id
            )
            chit?.tile = tile.id
            tile
        }
    }

    private fun createCorners(): List<Corner> {
        val corners = mutableMapOf<HexCoordinate, Corner>()

        tileLocations.forEach { location ->
            for (vector in cornerVectors) {
                val newLocation = location + vector
                val corner = Corner(UUID.randomUUID(), newLocation, null, null)
                corners[newLocation] = corner
            }
        }

        return corners.values.toList()
    }

    private fun createEdges(): List<Edge> {
        val edges = mutableMapOf<HexCoordinate, Edge>()

        tileLocations.forEach { location ->
            for (vector in edgeVectors) {
                val newLocation = location + vector
                val edge = Edge(UUID.randomUUID(), false, null)
                edges[newLocation] = edge
            }
        }

        return edges.values.toList()
    }

    companion object {
        private val tileLocations = listOf(
            HexCoordinate(0, -12), HexCoordinate(6, -12), HexCoordinate(12, -12),
            HexCoordinate(-6, -6), HexCoordinate(0, -6), HexCoordinate(6, -6), HexCoordinate(12, -6),
            HexCoordinate(-12, 0), HexCoordinate(-6, 0), HexCoordinate(0, 0), HexCoordinate(6, 0), HexCoordinate(12, 0),
            HexCoordinate(6, 6), HexCoordinate(0, 6), HexCoordinate(-6, 6), HexCoordinate(-12, 6),
            HexCoordinate(0, 12), HexCoordinate(-6, 12), HexCoordinate(-12, 12)
        )

        private val tileTypes = mutableMapOf(
            ResourceType.DESERT to 1,
            ResourceType.FIELD to 4,
            ResourceType.FOREST to 4,
            ResourceType.PASTURE to 4,
            ResourceType.HILL to 3,
            ResourceType.MOUNTAIN to 3
        )

        private val cornerVectors = listOf(
            HexCoordinate(2, -4), HexCoordinate(4, -2), HexCoordinate(2, 2),
            HexCoordinate(-2, 4), HexCoordinate(-4, 2), HexCoordinate(-2, -2)
        )

        private val edgeVectors = listOf(
            HexCoordinate(-3, 0), HexCoordinate(0, -3), HexCoordinate(-3, 3),
            HexCoordinate(3, 0), HexCoordinate(0, 3), HexCoordinate(3, -3)
        )

        private val chitNumbers =
            listOf(2) + (3..6).flatMap { listOf(it, it) } + (8..11).flatMap { listOf(it, it) } + 12
    }
}
