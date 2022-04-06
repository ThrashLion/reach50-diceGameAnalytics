package adaptingThreshold

import GameRuleset

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
        val averageRounds: Double = runForThreshold(0,accountThresholdMap[0]!!)
        println("average rounds needed: $averageRounds")
    }

    fun runForThreshold(account: Int, threshold: Int): Double {
        var sumOfAllRounds = 0
        for (i in 1..sampleSize) {
            val game = GameInstance(account, threshold, accountThresholdMap, ruleset)
            sumOfAllRounds += game.gameTurn()
        }
        return sumOfAllRounds.toDouble() / sampleSize.toDouble()
    }
}