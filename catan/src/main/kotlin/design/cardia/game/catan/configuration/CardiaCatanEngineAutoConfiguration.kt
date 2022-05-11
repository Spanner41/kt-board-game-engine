package design.cardia.game.catan.configuration

import design.cardia.game.catan.builder.CatanBuilder
import design.cardia.game.catan.entity.Catan
import design.cardia.game.catan.game.usecase.CreateGame
import design.cardia.game.catan.game.usecase.GetGame
import design.cardia.game.catan.game.usecase.PlaceRoad
import design.cardia.game.catan.game.usecase.UpdateGame
import design.cardia.game.catan.processor.CatanEventProcessor
import design.cardia.game.catan.repository.IReadRepository
import design.cardia.game.catan.repository.IWriteRepository
import design.cardia.game.engine.event.EventConsumer
import design.cardia.game.engine.event.EventDequeue
import design.cardia.game.engine.event.EventEnqueue
import design.cardia.game.engine.event.EventRegistry
import design.cardia.game.engine.event.InMemoryEventQueue
import design.cardia.game.engine.event.InMemoryEventRegistry
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnBean(value = [IReadRepository::class, IWriteRepository::class])
open class CardiaCatanEngineAutoConfiguration {
    @Bean
    open fun queue() = InMemoryEventQueue<Catan>()

    @Bean
    open fun registry() = InMemoryEventRegistry<Catan>()

    @Bean
    open fun createGame(enqueue: EventEnqueue<Catan>) = CreateGame(enqueue)

    @Bean
    open fun placeRoad(enqueue: EventEnqueue<Catan>) = PlaceRoad(enqueue)

    @Bean
    open fun getGame(readRepository: IReadRepository) = GetGame(readRepository)

    @Bean
    open fun updateGame(placeRoad: PlaceRoad) = UpdateGame(placeRoad)

    @Bean
    open fun catanBuilder() = CatanBuilder()

    @Bean
    open fun eventConsumer(dequeue: EventDequeue<Catan>, registry: EventRegistry<Catan>) =
        EventConsumer(dequeue, registry)

    @Bean
    open fun catanEventProcessor(
        writeRepository: IWriteRepository,
        readRepository: IReadRepository,
        catanBuilder: CatanBuilder
    ) =
        CatanEventProcessor(writeRepository, readRepository, catanBuilder)
}
