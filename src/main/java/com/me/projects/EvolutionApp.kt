package com.me.projects

import com.me.projects.game.gui.GUI

fun main(args: Array<String>) {
    val gui = GUI()
    val pointsList = ArrayList<Pair<Int, Int>>()
    pointsList.add(Pair<Int, Int>(300, 300))
    pointsList.add(Pair<Int, Int>(500, 500))
    pointsList.add(Pair<Int, Int>(600, 400))
    gui.draw(pointsList)

    Thread.sleep(2000)

    pointsList.add(Pair<Int, Int>(100, 300))
    pointsList.add(Pair<Int, Int>(100, 500))
    pointsList.add(Pair<Int, Int>(300, 400))
    gui.draw(pointsList)
}
