package com.me.projects

import com.me.projects.character.Cell
import com.me.projects.game.gui.GUI
import com.me.projects.game.logic.killCellsOnLeftSide

fun main() {
    val gui = GUI()
    val pointsList = ArrayList<IntArray>()

    val cell1 = Cell(intArrayOf(995, 400), doubleArrayOf())
    val cell2 = Cell(intArrayOf(300, 200), doubleArrayOf())

    val cells = arrayListOf(cell1, cell2)

    for (cell in cells) {
        pointsList.add(cell.coordinates)
    }

    gui.draw(pointsList)
    Thread.sleep(2000)

    killCellsOnLeftSide(cells)

    pointsList.clear()

    for (cell in cells) {
        pointsList.add(cell.coordinates)
    }

    gui.draw(pointsList)
    Thread.sleep(2000)
}
