package adaptingThreshold

import GameRuleset
import ValueSigma
import service.CalculatorService

class EmpiricalAnalysis (private val ruleset: GameRuleset, var sampleSize: Int) {
    var accountThresholdMap = HashMap<Int,Int>()

    fun runFullAnalysis() {
        println("## empirical analysis for adapting threshold ##")
        println("account | threshold")
        reverseApproach()
    }

    fun reverseApproach() {
        for (account in (ruleset.goal-1)downTo 0) {
            var thresholdAverageMap = HashMap<Int,Double>()
            for (threshold in 1..(ruleset.goal-account)) {
                thresholdAverageMap.put(threshold,runForThreshold(account, threshold))
            }
            var minimum = thresholdAverageMap.minByOrNull { it.value }!!
            accountThresholdMap.put(account,minimum.key)
            //println("account ${minimum.key} ${minimum.value}")
            println("$account ${minimum.key}")
        }
        val game = GameInstance(ruleset.goal, accountThresholdMap[0]!!, accountThresholdMap, ruleset)
        val resultSigma = runAdaptingThreshold()
        println("Adapting threshold average rounds: $resultSigma")
    }

    fun runForThreshold(account: Int, threshold: Int): Double {
        var sumOfAllRounds = 0
        for (i in 1..sampleSize) {
            val game = GameInstance(account, threshold, accountThresholdMap, ruleset)
            sumOfAllRounds += game.gameTurn()
        }
        return sumOfAllRounds.toDouble() / sampleSize.toDouble()
    }

    fun runAdaptingThreshold(): ValueSigma {
        var resultList: MutableList<Int> = mutableListOf<Int>()
        var sumOfAllRounds = 0
        for (i in 1..sampleSize) {
            val game = GameInstance(0, accountThresholdMap[0]!!, accountThresholdMap, ruleset)
            val rounds = game.gameTurn()
            resultList.add(rounds)
            sumOfAllRounds += rounds
        }
        val average = sumOfAllRounds.toDouble() / sampleSize.toDouble()
        val sigma = CalculatorService.calculateSigma(resultList,average,sampleSize)
        return ValueSigma(average,sigma)
    }
}