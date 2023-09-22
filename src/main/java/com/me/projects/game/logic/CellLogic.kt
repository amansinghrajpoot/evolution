package com.me.projects.game.logic

import com.me.projects.character.Cell
import com.me.projects.character.movement.moveCellToDirection
import com.me.projects.character.movement.selectIndexBasedOnProbability
import com.me.projects.game.gui.GUI

val GUI_FRAME = GUI()
fun killCellsOnLeftSide(cells: ArrayList<Cell>) {
    val cellsToRemove: MutableSet<Cell> = mutableSetOf()
    for (cell in cells) {
        if (cell.coordinates[0] <= GUI.FRAME_WIDTH / 2) {
            cellsToRemove.add(cell)
        }
    }
    cells.removeAll(cellsToRemove.toSet())
}

fun paintCellsOnGUI(cells: ArrayList<Cell>) {
    val pointsList = ArrayList<IntArray>()
    for (cell in cells) {
        pointsList.add(cell.coordinates)
    }
    GUI_FRAME.draw(pointsList)
}

fun makeCellsMove(cells: ArrayList<Cell>) {
    for (cell in cells) {
        val direction = selectIndexBasedOnProbability(cell.genes)
        moveCellToDirection(direction, cell.coordinates)
    }
}
