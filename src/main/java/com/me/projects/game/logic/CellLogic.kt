package com.me.projects.game.logic

import com.me.projects.character.Cell
import com.me.projects.game.gui.GUI

fun killCellsOnLeftSide(cells: ArrayList<Cell>) {
    val cellsToRemove: MutableSet<Cell> = mutableSetOf()
    for (cell in cells) {
        if (cell.coordinates[0] <= GUI.FRAME_WIDTH / 2) {
            cellsToRemove.add(cell)
        }
    }
    cells.removeAll(cellsToRemove.toSet())
}
