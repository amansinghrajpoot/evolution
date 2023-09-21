package me.projects.ml.character.movement

import kotlin.random.Random

fun selectIndexBasedOnProbability(probabilities: List<Double>): Directions {
    if (probabilities.isEmpty() || probabilities.size != 9) {
        return Directions.CENTER
    }

    val normalizedProbabilities = probabilities.map { it / probabilities.sum() }
    val randomValue = Random.nextDouble()

    var cumulativeProbability = 0.0
    for (i in normalizedProbabilities.indices) {
        cumulativeProbability += normalizedProbabilities[i]
        if (randomValue <= cumulativeProbability) {
            return Directions.values().find { it.ordinal == i }!!
        }
    }

    return Directions.CENTER
}
