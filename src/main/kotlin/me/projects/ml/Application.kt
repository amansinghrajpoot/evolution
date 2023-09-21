package me.projects.ml

import me.projects.ml.character.movement.selectIndexBasedOnProbability

class Application

fun main() {
    println("running application")
    val dir = listOf<Double>(0.1, 0.2, 0.1, 0.1, 0.4, 0.0, 0.0, 0.0, 0.0)
    println(selectIndexBasedOnProbability(dir))
}
