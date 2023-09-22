package com.me.projects.character.movement

import kotlin.random.Random

fun selectIndexBasedOnProbability(probabilities: List<Double>): Direction {
    if (probabilities.isEmpty() || probabilities.size != 9) {
        return Direction.CENTER
    }

    val normalizedProbabilities = probabilities.map { it / probabilities.sum() }
    val randomValue = Random.nextDouble()

    var cumulativeProbability = 0.0
    for (i in normalizedProbabilities.indices) {
        cumulativeProbability += normalizedProbabilities[i]
        if (randomValue <= cumulativeProbability) {
            return Direction.values().find { it.ordinal == i }!!
        }
    }

    return Direction.CENTER
}