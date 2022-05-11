package design.cardia.game.engine.event

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID

@ExtendWith(MockKExtension::class)
internal class EventConsumerTest {
    @InjectMockKs
    private lateinit var subject: EventConsumer<MockGame>

    @MockK
    private lateinit var queue: EventDequeue<MockGame>

    @SpyK
    private var registry = InMemoryEventRegistry<MockGame>()

    @Test
    fun `it should poll queue and run events`() {
        val event = MockEvent("")

        var callCount = 0
        registry.register<MockEvent> { callCount++ }

        coEvery { queue.poll() } returns event

        subject.runWhile { callCount < 5 }

        assertThat(callCount).isEqualTo(5)
        verify(exactly = 5) { registry.notify(any()) }
    }

    class MockGame : Game(UUID.randomUUID())
    data class MockEvent(val message: String) : Event<MockGame>(UUID.randomUUID())
}
