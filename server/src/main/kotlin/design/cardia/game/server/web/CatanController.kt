package design.cardia.game.server.web

import design.cardia.game.catan.game.usecase.CreateGame
import design.cardia.game.catan.game.usecase.GetGame
import design.cardia.game.catan.game.usecase.UpdateGame
import design.cardia.game.engine.event.Game
import design.cardia.game.server.model.GameCreateRequest
import design.cardia.game.server.model.GameUpdateRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(value = ["/catan"])
class CatanController(
    private val getGame: GetGame,
    private val createGame: CreateGame,
    private val updateGame: UpdateGame
) {
    @GetMapping(value = ["/{id}"])
    fun getById(@PathVariable id: UUID): ResponseEntity<Game> {
        val game = getGame(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(game)
    }

    @PostMapping(value = ["/"])
    fun create(@RequestBody request: GameCreateRequest): UUID {
        return createGame(request.players)
    }

    @PostMapping(value = ["/action"])
    fun update(@RequestBody request: GameUpdateRequest) {
        updateGame(request.game, request.player, request.action, request.attributes)
    }
}
