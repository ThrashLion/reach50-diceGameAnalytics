class GameInstance (val threshold: Int, val ruleset: GameRuleset) {
    private var rounds: Int = 0
    private var account: Int = 0
    private var cash: Int = 0

    fun gameTurn(): Int {
        val diceValue = rollTheDice()
        println("R("+rounds+")A("+account+")C("+cash+") D:"+diceValue)
        if (diceValue == ruleset.highestDiceValue) {
            println("lose progress")
            loseProgress()
            endRound()
        } else {
            cash += diceValue
            if ((account+cash) >= ruleset.goal) {
                return rounds
            }
            if (cash >= threshold) {
                saveProgress()
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

    private fun loseProgress() {
        cash = 0
    }

    private fun endRound() {
        rounds += 1
    }

    fun rollTheDice(): Int {
        return (ruleset.lowestDiceValue until (ruleset.highestDiceValue+1)).random()
    }

    private fun worstCase(): Boolean {
        return (rounds >= ruleset.goal)
    }

}