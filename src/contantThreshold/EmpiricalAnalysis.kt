package contantThreshold

import GameRuleset
import ValueSigma
import kotlin.math.pow

class EmpiricalAnalysis (val ruleset: GameRuleset, var sampleSize: Int) {

    fun runFullAnalysis() {
        println("## empirical analysis for constant threshold ##")
        println("threshold | averageRounds")
        for (threshold in 0..(ruleset.goal) ) {
            runForThreshold(threshold)
        }
    }

    fun runForThreshold(threshold: Int) {
        var resultList: MutableList<Int> = mutableListOf<Int>()
        var sumOfAllRounds = 0
        for (i in 1..sampleSize) {
            val game = GameInstance(threshold, ruleset)
            val rounds = game.gameTurn()
            resultList.add(rounds)
            sumOfAllRounds += rounds
        }
        val average = sumOfAllRounds.toDouble() / sampleSize.toDouble()
        val sigma = calculateSigma(resultList,average)
        val resultSigma = ValueSigma(average,sigma)
        println("$threshold $resultSigma")
    }

    private fun calculateSigma(resultList: MutableList<Int>, average: Double): Double {
        var sumOfSquares: Double = 0.0
        for (i in 0 until sampleSize) {
            sumOfSquares += (resultList[i]-average).pow(2)
        }
        return (1/(sampleSize.toDouble()-1)).pow(0.5)
    }
}