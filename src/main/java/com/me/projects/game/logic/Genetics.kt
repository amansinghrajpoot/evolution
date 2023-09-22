package com.me.projects.game.logic

import com.me.projects.character.Cell
import kotlin.random.Random

fun mutateGenes(cells: ArrayList<Cell>) {
    for (cell in cells) {
        val firstMutatingGeneIndex = cell.genes.indexOfFirst { it > 0.0 }
        if (cell.genes[firstMutatingGeneIndex] - 0.1 <= 0.0){
            break
        }
        val secondMutatingGeneIndex = Random.nextInt(0, )
    }
}
