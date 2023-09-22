package com.me.projects.character.movement

import com.me.projects.game.gui.GUI.CELL_START_WIDTH_BOUNDARY
import com.me.projects.game.gui.GUI.CELL_END_WIDTH_BOUNDARY
import com.me.projects.game.gui.GUI.CELL_END_HEIGHT_BOUNDARY
import com.me.projects.game.gui.GUI.CELL_START_HEIGHT_BOUNDARY

fun moveCellToDirection(direction: Direction, coordinate: IntArray) {
    val newX = coordinate[0]
    val newY = coordinate[1]

    when (direction) {
        Direction.CENTER -> {}

        Direction.UP -> {
            val updatedY = newY - 5
            if (isValidCoordinate(newX, updatedY)) {
                coordinate[1] = updatedY
            }
        }

        Direction.DOWN -> {
            val updatedY = newY + 5
            if (isValidCoordinate(newX, updatedY)) {
                coordinate[1] = updatedY
            }
        }

        Direction.LEFT -> {
            val updatedX = newX - 5
            if (isValidCoordinate(updatedX, newY)) {
                coordinate[0] = updatedX
            }
        }

        Direction.RIGHT -> {
            val updatedX = newX + 5
            if (isValidCoordinate(updatedX, newY)) {
                coordinate[0] = updatedX
            }
        }

        Direction.TOP_LEFT -> {
            val updatedX = newX - 5
            val updatedY = newY - 5
            if (isValidCoordinate(updatedX, updatedY)) {
                coordinate[0] = updatedX
                coordinate[1] = updatedY
            }
        }

        Direction.TOP_RIGHT -> {
            val updatedX = newX + 5
            val updatedY = newY - 5
            if (isValidCoordinate(updatedX, updatedY)) {
                coordinate[0] = updatedX
                coordinate[1] = updatedY
            }
        }

        Direction.BOTTOM_LEFT -> {
            val updatedX = newX - 5
            val updatedY = newY + 5
            if (isValidCoordinate(updatedX, updatedY)) {
                coordinate[0] = updatedX
                coordinate[1] = updatedY
            }
        }

        Direction.BOTTOM_RIGHT -> {
            val updatedX = newX + 5
            val updatedY = newY + 5
            if (isValidCoordinate(updatedX, updatedY)) {
                coordinate[0] = updatedX
                coordinate[1] = updatedY
            }
        }
    }
}

fun isValidCoordinate(x: Int, y: Int): Boolean {
    return x in CELL_START_WIDTH_BOUNDARY..CELL_END_WIDTH_BOUNDARY
            && y in CELL_START_HEIGHT_BOUNDARY..CELL_END_HEIGHT_BOUNDARY
}
