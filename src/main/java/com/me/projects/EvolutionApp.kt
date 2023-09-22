package com.me.projects

import com.me.projects.character.Cell
import com.me.projects.game.logic.makeCellsMove
import com.me.projects.game.logic.mutateGenes
import com.me.projects.game.logic.paintCellsOnGUI

fun main() {
    val cell = Cell(intArrayOf(500, 350), doubleArrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0))
    val cells = arrayListOf(cell)

    paintCellsOnGUI(cells, "Generation: 1")
    for (i in 1..700) {
        makeCellsMove(cells)
        Thread.sleep(50)
        if (i % 10 == 0) {
            paintCellsOnGUI(cells, "Generation: 1")
        }
        if (i % 200 == 0) {
            mutateGenes(cells)
        }
    }
    paintCellsOnGUI(cells, "Generation: 1")
}
