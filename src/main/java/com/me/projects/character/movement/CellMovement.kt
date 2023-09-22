package com.me.projects.character.movement

fun moveCellToDirection(direction: Direction, coordinate: IntArray) {
    return when (direction) {
        Direction.CENTER -> {}
        Direction.UP -> { coordinate[1] -= 5 }
        Direction.DOWN -> { coordinate[1] += 5 }
        Direction.LEFT -> { coordinate[0] -= 5 }
        Direction.RIGHT -> { coordinate[0] += 5 }
        Direction.TOP_LEFT -> {
            coordinate[0] -= 5
            coordinate[1] -= 5
        }
        Direction.TOP_RIGHT -> {
            coordinate[1] -= 5
            coordinate[0] += 5
        }
        Direction.BOTTOM_LEFT -> {
            coordinate[1] += 5
            coordinate[0] -= 5
        }
        Direction.BOTTOM_RIGHT -> {
            coordinate[1] += 5
            coordinate[0] += 5
        }
    }
}
