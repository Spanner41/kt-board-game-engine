package design.cardia.game.catan.builder

import assertk.assertThat
import assertk.assertions.hasSize
import design.cardia.game.catan.event.GameCreatedEvent
import org.junit.jupiter.api.Test
import java.util.UUID

internal class CatanBuilderTest {
    private val subject = CatanBuilder()

    private val createEvent = GameCreatedEvent(UUID.randomUUID(), listOf(UUID.randomUUID()))

    private val result = subject.createGame(createEvent)

    @Test
    fun `it should have 19 tiles`() {
        assertThat(result.board.tiles).hasSize(19)
    }

    @Test
    fun `it should have 54 corners`() {
        assertThat(result.board.corners).hasSize(54)
    }

    @Test
    fun `it should have 72 edges`() {
        assertThat(result.board.edges).hasSize(72)
    }
}
