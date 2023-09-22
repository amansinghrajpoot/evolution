package com.me.projects

import com.me.projects.character.movement.Direction
import com.me.projects.character.movement.moveCellToDirection
import com.me.projects.game.gui.GUI

fun main() {
    val gui = GUI()
    val pointsList = ArrayList<IntArray>()

    pointsList.add(intArrayOf(600, 400))
    gui.draw(pointsList)

    Thread.sleep(2000)

    moveCellToDirection(Direction.UP, pointsList[0])
    gui.draw(pointsList)

    Thread.sleep(2000)
}
