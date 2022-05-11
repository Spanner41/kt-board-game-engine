package design.cardia.game.engine.event

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import java.lang.Thread.sleep
import java.util.UUID

internal class EventQueueTest {
    private val subject = InMemoryEventQueue<MockGame>()

    @Test
    fun `it should give events in order`() {
        val events = listOf(MockEvent("1"), MockEvent("2"), MockEvent("3"))

        events.forEach {
            subject.put(it)
            sleep(3)
        }

        repeat(3) { assertThat(subject.poll()).isEqualTo(events[it]) }
    }

    class MockGame : Game(UUID.randomUUID())
    data class MockEvent(val message: String) : Event<MockGame>(UUID.randomUUID())
}
