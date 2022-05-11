package design.cardia.game.engine.event

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import assertk.fail
import org.junit.jupiter.api.Test
import java.util.UUID

internal class EventRegistryTest {
    private val subject = InMemoryEventRegistry<MockGame>()

    @Test
    fun `it should notify when an event occurs`() {
        var called = false
        subject.register<CorrectEvent> { called = true }
        subject.register<IncorrectEvent> { fail("Incorrect Event should not be called") }

        assertThat(called).isFalse()

        val event = CorrectEvent("Test Message")
        subject.notify(event)

        assertThat(called).isTrue()
    }

    @Test
    fun `it should not notify after handler is removed`() {
        var called = false
        val handler = subject.register<CorrectEvent> { called = true }
        subject.deRegister(handler)

        val event = CorrectEvent("Test Message")
        subject.notify(event)

        assertThat(called).isFalse()
    }

    class MockGame : Game(UUID.randomUUID())
    data class CorrectEvent(val message: String) : Event<MockGame>(UUID.randomUUID())
    data class IncorrectEvent(val message: String) : Event<MockGame>(UUID.randomUUID())
}
