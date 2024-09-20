package com.me.projects.game.logic

import com.me.projects.character.Cell
import com.me.projects.character.Predator
import com.me.projects.character.movement.moveCellToDirection
import com.me.projects.character.movement.selectIndexBasedOnProbability
import com.me.projects.game.gui.PrintablePredator
import com.me.projects.game.util.ApplicationConstants.GENDER_INDEX
import com.me.projects.game.util.ApplicationConstants.KILLING_RADIUS
import com.me.projects.game.util.ApplicationConstants.LOCO_GENES_SIZE
import kotlin.math.sqrt


fun paintPredatorsOnGUI(predators: ArrayList<Predator>) {
    val printablePredatorsList = ArrayList<PrintablePredator>()
    for (predator in predators) {
        val printablePredator = PrintablePredator()
        printablePredator.coordinates = predator.coordinates
        printablePredator.gender = predator.genes[GENDER_INDEX]
        printablePredatorsList.add(printablePredator)
    }
    GUI_FRAME.drawPredator(printablePredatorsList)
}

fun makePredatorMove(predators: ArrayList<Predator>) {
    for (predator in predators) {
        val direction = selectIndexBasedOnProbability(predator.genes.copyOf(LOCO_GENES_SIZE))
        moveCellToDirection(direction, predator.coordinates)
    }
}

fun predatorKillCells(predators: ArrayList<Predator>, cells: ArrayList<Cell>): Int {
    val initialSize = cells.size
    val cellsToRemove: MutableSet<Cell> = mutableSetOf()
    for (cell in cells) {
        for (predator in predators) {
            if (calculateDistance(cell.coordinates, predator.coordinates) <= KILLING_RADIUS) {
                cellsToRemove.add(cell)
            }
        }
    }
    cells.removeAll(cellsToRemove.toSet())
    return initialSize - cells.size
}

private fun calculateDistance(coord1: IntArray, coord2: IntArray): Double {
    val xDiff = coord1[0] - coord2[0]
    val yDiff = coord1[1] - coord2[1]
    return sqrt((xDiff * xDiff + yDiff * yDiff).toDouble())
}