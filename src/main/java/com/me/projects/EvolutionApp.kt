package com.me.projects

import com.me.projects.game.gui.GUI

fun main(args: Array<String>) {
    val pointsList = ArrayList<Pair<Int, Int>>()
    pointsList.add(Pair<Int, Int>(300, 300))
    pointsList.add(Pair<Int, Int>(500, 500))
    pointsList.add(Pair<Int, Int>(600, 400))
    GUI().draw(pointsList)
}
