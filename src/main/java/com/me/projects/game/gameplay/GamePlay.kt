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
import com.me.projects.game.util.ApplicationConstants.MAXIMUM_CELL_STEPS
import com.me.projects.game.util.ApplicationConstants.MAX_GENERATIONS
import com.me.projects.game.util.ApplicationConstants.REFRESH_RATE
import com.me.projects.game.util.ApplicationConstants.THREAD_SLEEP_MS
import kotlin.random.Random

fun startGame() {
    var generation = 1
    var kills = 0
    var currentPopulation = 0
    var mutated = 0
    var reproduced = 0

    val cells = arrayListOf(
        Cell(intArrayOf(500, 350), doubleArrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0)),
        Cell(intArrayOf(200, 200), doubleArrayOf(0.4, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 1.0, 0.0)),
        Cell(intArrayOf(540, 400), doubleArrayOf(0.1, 0.0, 0.5, 0.1, 0.1, 0.1, 0.0, 1.0, 0.0)),
        Cell(intArrayOf(349, 500), doubleArrayOf(0.2, 0.0, 0.2, 0.0, 0.0, 0.0, 0.0, 1.0, 0.5)),
        Cell(intArrayOf(349, 500), doubleArrayOf(0.0, 0.0, 0.2, 0.2, 0.0, 0.0, 0.0, 1.0, 0.5)),
        Cell(intArrayOf(349, 100), doubleArrayOf(0.1, 0.0, 0.2, 0.0, 0.2, 0.1, 0.3, 1.0, 0.0)),
        Cell(intArrayOf(300, 105), doubleArrayOf(0.0, 0.0, 0.0, 0.1, 0.0, 0.3, 0.0, 1.0, 0.5)),
        Cell(intArrayOf(500, 431), doubleArrayOf(0.0, 0.1, 0.1, 0.4, 0.1, 0.0, 0.0, 1.0, 0.1)),
        Cell(intArrayOf(234, 234), doubleArrayOf(0.0, 0.0, 0.2, 0.2, 0.0, 0.1, 0.2, 1.0, 0.1)),
        Cell(intArrayOf(432, 500), doubleArrayOf(0.0, 0.0, 0.2, 0.3, 0.3, 0.0, 0.0, 1.0, 0.0)),
        Cell(intArrayOf(123, 433), doubleArrayOf(0.0, 0.0, 0.0, 0.2, 0.0, 0.0, 0.2, 1.0, 0.5)),
    )

    for (i in 1..MAX_GENERATIONS) {
        for (j in 1..MAXIMUM_CELL_STEPS) {
            currentPopulation = cells.size
            makeCellsMove(cells)
            Thread.sleep(THREAD_SLEEP_MS)

            if (j % REFRESH_RATE == 0) {
                paintCellsOnGUI(cells, "Generation: $generation Kills: $kills Mutated: $mutated Reproduced: $reproduced")
            }
        }
        killCellsOnLeftSide(cells)
        kills = currentPopulation - cells.size
        generation += 1

        mutated = mutateGenes(cells)
        reproduced = reproduce(cells)
        repositionCells(cells)
    }
    paintCellsOnGUI(cells, "Generation: $generation Kills: $kills Mutated: $mutated Reproduced: $reproduced")
}

fun repositionCells(cells: ArrayList<Cell>) {
    for (cell in cells) {
        val newX = Random.nextInt(CELL_START_WIDTH_BOUNDARY, CELL_END_WIDTH_BOUNDARY)
        val newY = Random.nextInt(CELL_START_HEIGHT_BOUNDARY, CELL_END_HEIGHT_BOUNDARY)
        cell.coordinates = intArrayOf(newX, newY)
    }
}
