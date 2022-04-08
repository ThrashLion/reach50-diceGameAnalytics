package contantThreshold

import GameRuleset
import service.CalculatorService
import ValueSigma

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
        val sigma = CalculatorService.calculateSigma(resultList,average,sampleSize)
        val resultSigma = ValueSigma(average,sigma)
        println("$threshold $resultSigma")
    }

}