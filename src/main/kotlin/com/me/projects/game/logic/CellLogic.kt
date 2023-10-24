package com.me.projects.game.logic

import com.me.projects.character.Cell
import com.me.projects.character.movement.moveCellToDirection
import com.me.projects.character.movement.selectIndexBasedOnProbability
import com.me.projects.game.gui.GUI
import com.me.projects.game.gui.PrintableCell
import com.me.projects.game.util.ApplicationConstants.FRAME_WIDTH
import com.me.projects.game.util.ApplicationConstants.GENDER_INDEX
import com.me.projects.game.util.ApplicationConstants.LOCO_GENES_SIZE

val GUI_FRAME = GUI()

fun killCellsOnLeftSide(cells: ArrayList<Cell>) {
    val cellsToRemove: MutableSet<Cell> = mutableSetOf()
    for (cell in cells) {
        if (cell.coordinates[0] <= FRAME_WIDTH / 2) {
            cellsToRemove.add(cell)
        }
    }
    cells.removeAll(cellsToRemove.toSet())
}

fun paintCellsOnGUI(cells: ArrayList<Cell>, text: String) {
    val printableCellsList = ArrayList<PrintableCell>()
    for (cell in cells) {
        val printableCell = PrintableCell()
        printableCell.coordinates = cell.coordinates
        printableCell.gender = cell.genes[GENDER_INDEX]
        printableCellsList.add(printableCell)
    }
    GUI_FRAME.draw(printableCellsList, text)
}

fun makeCellsMove(cells: ArrayList<Cell>) {
    for (cell in cells) {
        val direction = selectIndexBasedOnProbability(cell.genes.copyOf(LOCO_GENES_SIZE))
        moveCellToDirection(direction, cell.coordinates)
    }
}
