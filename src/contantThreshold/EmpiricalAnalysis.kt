package contantThreshold

import GameRuleset

class EmpiricalAnalysis (val ruleset: GameRuleset, var sampleSize: Int) {

    fun runFullAnalysis() {
        println("## empirical analysis for constant threshold ##")
        println("threshold | averageRounds")
        for (threshold in 0..(ruleset.goal) ) {
            runForThreshold(threshold)
        }
    }

    fun runForThreshold(threshold: Int) {
        var sumOfAllRounds = 0
        for (i in 1..sampleSize) {
            val game = GameInstance(threshold, ruleset)
            sumOfAllRounds += game.gameTurn()
        }
        val average: Double = sumOfAllRounds.toDouble() / sampleSize.toDouble()
        println("" + threshold + " " + average)
    }
}