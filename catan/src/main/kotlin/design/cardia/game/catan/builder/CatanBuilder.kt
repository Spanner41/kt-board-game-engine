package design.cardia.game.catan.builder

import design.cardia.game.catan.entity.Board
import design.cardia.game.catan.entity.Catan
import design.cardia.game.catan.entity.Chit
import design.cardia.game.catan.entity.DevelopmentDeck
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
            players = createPlayers(event).associateBy { it.id },
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
            tiles = tiles.associateBy { it.location },
            corners = mapOf(),
            edges = mapOf(),
            chits = chits.associateBy { it.id },
            placedRoads = listOf(),
            placedSettlements = listOf(),
            placedCities = listOf(),
            robber = Robber(UUID.randomUUID())
        )
    }

    private fun createChits() = chitNumbers.map { Chit(UUID.randomUUID(), it, null) }

    private fun createTiles(chits: Iterable<Chit>): Iterable<Tile> {
        require(tileLocations.size - 1 == chits.count())

        val chitShuffler = Shuffler<Chit>().also { shuffler ->
            for (chit in chits) {
                shuffler.add(chit)
            }
        }

        val tileShuffler = Shuffler<ResourceType>().also { shuffler ->
            for ((type, count) in tilesTypes) {
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
            chit?.tile = tile
            tile
        }
    }

    companion object {
        private val tileLocations = listOf(
            HexCoordinate(0, -2), HexCoordinate(1, -2), HexCoordinate(2, -2),
            HexCoordinate(-1, -1), HexCoordinate(0, -1), HexCoordinate(1, -1), HexCoordinate(2, -1),
            HexCoordinate(-2, 0), HexCoordinate(-1, 0), HexCoordinate(0, 0), HexCoordinate(1, 0), HexCoordinate(2, 0),
            HexCoordinate(1, 1), HexCoordinate(1, 0), HexCoordinate(1, -1), HexCoordinate(1, -2),
            HexCoordinate(2, 0), HexCoordinate(2, -1), HexCoordinate(-2, 2)
        )

        private val tilesTypes = mutableMapOf(
            ResourceType.DESERT to 1,
            ResourceType.FIELD to 4,
            ResourceType.FOREST to 4,
            ResourceType.PASTURE to 4,
            ResourceType.HILL to 3,
            ResourceType.MOUNTAIN to 3
        )

        private val chitNumbers = (2..6) + (3..6) + (8..12) + (8..11)
    }
}
