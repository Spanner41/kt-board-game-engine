package design.cardia.game.server

import design.cardia.game.catan.configuration.CardiaCatanEngineAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@Import(value = [CardiaCatanEngineAutoConfiguration::class])
@EnableAsync
open class SpringConfiguration
