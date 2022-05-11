package design.cardia.game.catan.entity

open class Shuffler<E> : ArrayList<E>() {
    fun randomElement() = randomOrNull().also { remove(it) }
}
