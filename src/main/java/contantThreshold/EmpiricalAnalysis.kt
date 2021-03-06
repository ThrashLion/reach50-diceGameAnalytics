package contantThreshold

import GameRuleset
import service.CalculatorService
import ValueSigma
import service.OutputService

class EmpiricalAnalysis (val ruleset: GameRuleset, var sampleSize: Int) {

    fun runFullAnalysis() {
        println("## empirical analysis for constant threshold ##")
        println("threshold | averageRounds")
        val data = mutableListOf(listOf("threshold", "average rounds", "sigma"))
        for (threshold in 0..(ruleset.goal) ) {
            val result = runForThreshold(threshold)
            data.add(listOf(threshold.toString(), result.valueAsString(), result.sigmaAsString()))
        }
        OutputService.exportCSV(data)
    }

    fun runForThreshold(threshold: Int): ValueSigma {
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
        return ValueSigma(average,sigma)
    }

}