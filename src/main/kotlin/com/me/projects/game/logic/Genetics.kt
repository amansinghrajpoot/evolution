package com.me.projects.game.logic

import com.me.projects.character.Cell
import com.me.projects.game.gameplay.generateRandomGender
import com.me.projects.game.util.ApplicationConstants
import com.me.projects.game.util.ApplicationConstants.GENDER_INDEX
import com.me.projects.game.util.ApplicationConstants.GENES_SIZE
import com.me.projects.game.util.ApplicationConstants.LOCO_GENES_SIZE
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

            if (cells[firstParentIndex].genes[GENDER_INDEX] != cells[secondParentIndex].genes[GENDER_INDEX]) {
                val firstParentGenes = cells[firstParentIndex].genes
                val secondParentGenes = cells[secondParentIndex].genes

                val midpoint = (firstParentGenes.size - 1) / 2

                val secondHalfFirstParent = firstParentGenes.sliceArray(midpoint until firstParentGenes.size - 1)
                val firstHalfSecondParent = secondParentGenes.sliceArray(0 until midpoint)

                val genes = firstHalfSecondParent + secondHalfFirstParent
                val sum = genes.sum()
                val normalizedGenes = genes.map { it / sum }

                val newCell1 = Cell(
                    genes = (normalizedGenes + generateRandomGender()).toDoubleArray(),
                    coordinates = IntArray(
                        Random.nextInt(
                            ApplicationConstants.CELL_START_WIDTH_BOUNDARY,
                            ApplicationConstants.CELL_END_WIDTH_BOUNDARY
                        )
                    )
                )
                val newCell2 = Cell(
                    genes = (normalizedGenes + generateRandomGender()).toDoubleArray(),
                    coordinates = IntArray(
                        Random.nextInt(
                            ApplicationConstants.CELL_START_WIDTH_BOUNDARY,
                            ApplicationConstants.CELL_END_WIDTH_BOUNDARY
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
