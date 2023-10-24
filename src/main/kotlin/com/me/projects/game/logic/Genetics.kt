package com.me.projects.game.logic

import com.me.projects.character.Cell
import com.me.projects.game.gameplay.generateRandomGender
import com.me.projects.game.util.ApplicationConstants
import com.me.projects.game.util.ApplicationConstants.GENDER_INDEX
import com.me.projects.game.util.ApplicationConstants.GENES_SIZE
import com.me.projects.game.util.ApplicationConstants.LOCO_GENES_SIZE
import com.me.projects.game.util.ApplicationConstants.PROXIMITY_THRESHOLD
import kotlin.math.sqrt
import kotlin.random.Random

fun mutateGenes(cells: ArrayList<Cell>): Int {
    var count = 0
    for (cell in cells) {
        val nonZeroList = cell.genes.filter { it > 0.0 }
        val randomGene = Random.nextInt(0, nonZeroList.size)
        val firstMutatingGeneIndex = cell.genes.indexOfFirst { it == nonZeroList[randomGene] }
        if (cell.genes[firstMutatingGeneIndex] - 0.1 < 0.0) {
            break
        }
        val secondMutatingGeneIndex = Random.nextInt(0, GENES_SIZE)
        if (cell.genes[secondMutatingGeneIndex] + 0.1 > 1) {
            break
        }
        cell.genes[firstMutatingGeneIndex] -= 0.1
        cell.genes[secondMutatingGeneIndex] += 0.1
        count++
    }
    return count
}

fun reproduce(cells: ArrayList<Cell>): Int {
    var count = 0
    val cellSize = LOCO_GENES_SIZE - 1
    for (i in 0..cellSize) {
        try {
            val firstParentIndex = Random.nextInt(0, cellSize)
            val secondParentIndex = Random.nextInt(0, cellSize)

            val closeCells = selectCloseCells(cells)

            if (closeCells!= null && haveOppositeGender(cells[firstParentIndex], cells[secondParentIndex])) {
                val firstParentGenes = closeCells.first.genes
                val secondParentGenes = closeCells.second.genes

                val midpoint = (firstParentGenes.size - 1) / 2

                val secondHalfFirstParent = firstParentGenes.sliceArray(midpoint until firstParentGenes.size - 1)
                val firstHalfSecondParent = secondParentGenes.sliceArray(0 until midpoint)

                val genes = firstHalfSecondParent + secondHalfFirstParent
                val sum = genes.sum()
                val normalizedGenes = genes.map { it / sum }

                val newCell1 = Cell(
                    genes = (normalizedGenes + generateRandomGender()).toDoubleArray(),
                    coordinates = intArrayOf(
                        Random.nextInt(
                            ApplicationConstants.CELL_END_WIDTH_BOUNDARY / 2,
                            ApplicationConstants.CELL_END_WIDTH_BOUNDARY
                        ),
                        Random.nextInt(
                            ApplicationConstants.CELL_START_HEIGHT_BOUNDARY,
                            ApplicationConstants.CELL_END_HEIGHT_BOUNDARY
                        )
                    )
                )
                val newCell2 = Cell(
                    genes = (normalizedGenes + generateRandomGender()).toDoubleArray(),
                    coordinates = intArrayOf(
                        Random.nextInt(
                            ApplicationConstants.CELL_END_WIDTH_BOUNDARY / 2,
                            ApplicationConstants.CELL_END_WIDTH_BOUNDARY
                        ),
                        Random.nextInt(
                            ApplicationConstants.CELL_START_HEIGHT_BOUNDARY,
                            ApplicationConstants.CELL_END_HEIGHT_BOUNDARY
                        )
                    )
                )
                cells.add(newCell1)
                cells.add(newCell2)
                count += 2
            }
        } catch (_: Exception) { }
    }
    return count
}

private fun haveOppositeGender(cell1: Cell, cell2: Cell): Boolean {
    return cell1.genes[GENDER_INDEX] != cell2.genes[GENDER_INDEX]
}

private fun areCellsClose(cell1: Cell, cell2: Cell): Boolean {
    val distance = calculateEuclideanDistance(cell1, cell2)
    return distance <= PROXIMITY_THRESHOLD
}

private fun calculateEuclideanDistance(cell1: Cell, cell2: Cell): Int {
    val x1 = cell1.coordinates[0]
    val y1 = cell1.coordinates[1]
    val x2 = cell2.coordinates[0]
    val y2 = cell2.coordinates[1]

    val deltaX: Int = x2 - x1
    val deltaY: Int = y2 - y1

    return sqrt((deltaX * deltaX + deltaY * deltaY).toDouble()).toInt()
}

private fun selectCloseCells(cells: List<Cell>): Pair<Cell, Cell>? {
    for (i in 0 until cells.size - 1) {
        val cell1 = cells[i]
        for (j in i + 1 until cells.size) {
            val cell2 = cells[j]
            val distance = calculateEuclideanDistance(cell1, cell2)
            if (distance <= PROXIMITY_THRESHOLD) {
                return Pair(cell1, cell2)
            }
        }
    }
    return null
}
