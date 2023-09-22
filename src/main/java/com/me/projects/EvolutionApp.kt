package com.me.projects

import com.me.projects.character.Cell
import com.me.projects.game.logic.makeCellsMove
import com.me.projects.game.logic.mutateGenes
import com.me.projects.game.logic.paintCellsOnGUI

fun main() {
    val cell = Cell(intArrayOf(300, 200), doubleArrayOf(0.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 0.0))
    val cells = arrayListOf(cell)

    paintCellsOnGUI(cells)

    Thread.sleep(2000)

    for (i in 1..700) {
        makeCellsMove(cells)
        Thread.sleep(50)
        if (i % 10 == 0) {
            paintCellsOnGUI(cells)
        }
        if (i % 200 == 0) {
            mutateGenes(cells)
        }
    }
    paintCellsOnGUI(cells)
}
