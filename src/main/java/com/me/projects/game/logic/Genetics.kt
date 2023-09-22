package com.me.projects.game.logic

import com.me.projects.character.Cell
import com.me.projects.game.util.ApplicationConstants.GENES_SIZE
import kotlin.random.Random

fun mutateGenes(cells: ArrayList<Cell>) {
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
    }
}
