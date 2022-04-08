package adaptingThreshold

import GameRuleset

class GameInstance (private var account: Int, var threshold: Int, private val accountThresholdMap: HashMap<Int,Int>, private val ruleset: GameRuleset) {
    private var rounds: Int = 0
    private var cash: Int = 0
    private val debug = false

    fun gameTurn(): Int {
        val diceValue = rollTheDice()
        if(debug) println("R($rounds)A($account)C($cash) D:$diceValue")
        if (diceValue == ruleset.highestDiceValue) {
            loseProgress()
            endRound()
        } else {
            cash += diceValue
            if ((account+cash) >= ruleset.goal) {
                saveProgress()
                if(debug) println("#### threshold: $threshold rounds:$rounds ####")
                return rounds
            }
            if (cash >= threshold) {
                saveProgress()
                adaptThreshold()
                endRound()
            }
        }
        if(worstCase()) {
            return rounds
        }
        return gameTurn()
    }

    private fun saveProgress() {
        account += cash
        cash = 0
    }

    private fun adaptThreshold() {
        threshold = accountThresholdMap[account]!!
    }

    private fun loseProgress() {
        cash = 0
    }

    private fun endRound() {
        rounds += 1
    }

    fun rollTheDice(): Int {
        return ruleset.dice.roll()
    }

    private fun worstCase(): Boolean {
        return (rounds >= ruleset.goal)
    }

}