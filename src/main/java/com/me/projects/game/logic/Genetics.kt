package com.me.projects.game.logic

import com.me.projects.character.Cell
import com.me.projects.game.util.ApplicationConstants.GENES_SIZE
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
    for (i in 0..cells.size) {
        try {
            val firstParentIndex = Random.nextInt(0, cells.size)
            val secondParentIndex = Random.nextInt(0, cells.size)

            val firstParentGenes = cells[firstParentIndex].genes
            val secondParentGenes = cells[secondParentIndex].genes

            val midpoint = firstParentGenes.size / 2

            val secondHalfFirstParent = firstParentGenes.sliceArray(midpoint until firstParentGenes.size)
            val firstHalfSecondParent = secondParentGenes.sliceArray(0 until midpoint)

            cells[firstParentIndex].genes = firstHalfSecondParent + secondHalfFirstParent
            cells[secondParentIndex].genes = firstHalfSecondParent + secondHalfFirstParent
            count++
        } catch (_: Exception) { }
    }
    return count
}
