package com.me.projects.game.gameplay

import com.me.projects.character.Cell
import com.me.projects.character.Predator
import com.me.projects.game.logic.killCellsOnLeftSide
import com.me.projects.game.logic.makeCellsMove
import com.me.projects.game.logic.makePredatorMove
import com.me.projects.game.logic.mutateGenes
import com.me.projects.game.logic.paintCellsOnGUI
import com.me.projects.game.logic.paintPredatorsOnGUI
import com.me.projects.game.logic.predatorKillCells
import com.me.projects.game.logic.reproduce
import com.me.projects.game.util.ApplicationConstants.CELL_END_HEIGHT_BOUNDARY
import com.me.projects.game.util.ApplicationConstants.CELL_END_WIDTH_BOUNDARY
import com.me.projects.game.util.ApplicationConstants.CELL_START_HEIGHT_BOUNDARY
import com.me.projects.game.util.ApplicationConstants.CELL_START_WIDTH_BOUNDARY
import com.me.projects.game.util.ApplicationConstants.KILLING_RATE
import com.me.projects.game.util.ApplicationConstants.LOCO_GENES_SIZE
import com.me.projects.game.util.ApplicationConstants.MAXIMUM_CELL_STEPS
import com.me.projects.game.util.ApplicationConstants.MAX_GENERATIONS
import com.me.projects.game.util.ApplicationConstants.MAX_POPULATION
import com.me.projects.game.util.ApplicationConstants.MAX_PREDATOR_POPULATION
import com.me.projects.game.util.ApplicationConstants.MUTATION_RATE
import com.me.projects.game.util.ApplicationConstants.REFRESH_RATE
import com.me.projects.game.util.ApplicationConstants.REPRODUCTION_RATE
import com.me.projects.game.util.ApplicationConstants.THREAD_SLEEP_MS
import kotlin.random.Random

fun startGame() {
    var generation = 1
    var killed = 0
    var currentPopulation = 0
    var mutated = 0
    var reproduced = 0

    val cells = generateRandomCells()
    val predators = generateRandomPredators()

    for (i in 1..MAX_GENERATIONS) {
        for (j in 1..MAXIMUM_CELL_STEPS) {
            currentPopulation = cells.size
            makeCellsMove(cells)
            makePredatorMove(predators)
            Thread.sleep(THREAD_SLEEP_MS)

            killed += predatorKillCells(predators, cells)

            if (j % REFRESH_RATE == 0) {
                paintCellsOnGUI(cells, "Population: $currentPopulation Generation: $generation Killed: $killed Mutated: $mutated Reproduced: $reproduced")
                paintPredatorsOnGUI(predators)
            }
            if (j % REPRODUCTION_RATE == 0) reproduced += reproduce(cells)

            if (j % KILLING_RATE == 0) {
                killed += killCellsOnLeftSide(cells)
            }
            if (j % MUTATION_RATE == 0) {
                mutated += mutateGenes(cells)
            }
        }
        killed = 0
        mutated = 0
        reproduced = 0
        generation += 1

        // repositionCells(cells)
    }
}

fun repositionCells(cells: ArrayList<Cell>) {
    for (cell in cells) {
        val newX = Random.nextInt(CELL_START_WIDTH_BOUNDARY, CELL_END_WIDTH_BOUNDARY)
        val newY = Random.nextInt(CELL_START_HEIGHT_BOUNDARY, CELL_END_HEIGHT_BOUNDARY)
        cell.coordinates = intArrayOf(newX, newY)
    }
}

fun generateRandomCells(): ArrayList<Cell> {
    val cells = arrayListOf<Cell>()
    for (i in 0 until MAX_POPULATION) {
        val x = Random.nextInt(CELL_START_WIDTH_BOUNDARY, CELL_END_WIDTH_BOUNDARY)
        val y = Random.nextInt(CELL_START_HEIGHT_BOUNDARY, CELL_END_HEIGHT_BOUNDARY)
        val genes = generateRandomGenes()
        val socialBehavior = Random.nextInt(0, 100)
        cells.add(Cell(intArrayOf(x, y), genes, socialBehavior))
    }
    return cells
}

fun generateRandomPredators(): ArrayList<Predator> {
    val predators = arrayListOf<Predator>()
    for (i in 0 until MAX_PREDATOR_POPULATION) {
        val x = Random.nextInt(CELL_START_WIDTH_BOUNDARY, CELL_END_WIDTH_BOUNDARY)
        val y = Random.nextInt(CELL_START_HEIGHT_BOUNDARY, CELL_END_HEIGHT_BOUNDARY)
        val genes = generateRandomGenes()
        predators.add(Predator(intArrayOf(x, y), genes))
    }
    return predators
}

fun generateRandomGenes(): DoubleArray {
    val genes = mutableListOf<Double>()
    repeat(LOCO_GENES_SIZE) {
        genes.add(Random.nextDouble(0.0, 1.0))
    }
    val sum = genes.sum()
    if (sum > 1.0) {
        val scaleFactor = 1.0 / sum
        for (i in genes.indices) {
            genes[i] *= scaleFactor
        }
    }
    val gender = generateRandomGender()
    return (genes + gender).toDoubleArray()
}

fun generateRandomGender(): Double {
    return if (Random.nextBoolean()) 1.0 else 0.0
}
