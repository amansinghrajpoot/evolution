package com.me.projects.game.gameplay

import com.me.projects.character.Cell
import com.me.projects.game.logic.killCellsOnLeftSide
import com.me.projects.game.logic.makeCellsMove
import com.me.projects.game.logic.mutateGenes
import com.me.projects.game.logic.paintCellsOnGUI
import com.me.projects.game.logic.reproduce
import com.me.projects.game.util.ApplicationConstants.CELL_END_HEIGHT_BOUNDARY
import com.me.projects.game.util.ApplicationConstants.CELL_END_WIDTH_BOUNDARY
import com.me.projects.game.util.ApplicationConstants.CELL_START_HEIGHT_BOUNDARY
import com.me.projects.game.util.ApplicationConstants.CELL_START_WIDTH_BOUNDARY
import com.me.projects.game.util.ApplicationConstants.LOCO_GENES_SIZE
import com.me.projects.game.util.ApplicationConstants.MAXIMUM_CELL_STEPS
import com.me.projects.game.util.ApplicationConstants.MAX_GENERATIONS
import com.me.projects.game.util.ApplicationConstants.MAX_POPULATION
import com.me.projects.game.util.ApplicationConstants.REFRESH_RATE
import com.me.projects.game.util.ApplicationConstants.THREAD_SLEEP_MS
import kotlin.random.Random

fun startGame() {
    var generation = 1
    var killed = 0
    var currentPopulation = 0
    var mutated = 0
    var reproduced = 0

    val cells = generateRandomCells()

    for (i in 1..MAX_GENERATIONS) {
        for (j in 1..MAXIMUM_CELL_STEPS) {
            currentPopulation = cells.size
            makeCellsMove(cells)
            Thread.sleep(THREAD_SLEEP_MS)

            if (j % REFRESH_RATE == 0) {
                paintCellsOnGUI(cells, "Population: $currentPopulation Generation: $generation Killed: $killed Mutated: $mutated Reproduced: $reproduced")
            }
        }
        killCellsOnLeftSide(cells)
        killed = currentPopulation - cells.size
        generation += 1

        mutated = mutateGenes(cells)
        reproduced = reproduce(cells)
        repositionCells(cells)
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
        cells.add(Cell(intArrayOf(x, y), genes))
    }
    return cells
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

fun generateRandomGender(): Double{
    return if (Random.nextBoolean()) 1.0 else 0.0
}
