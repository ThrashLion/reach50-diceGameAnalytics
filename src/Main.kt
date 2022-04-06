fun main() {
    val vanillaRules = GameRuleset(1,6,50)
    val sampleSize = 100000000

    for (threshold in 0..(vanillaRules.goal) ) {
        runEmpiricalAnalysis(threshold, vanillaRules, sampleSize)
    }
    checkDice(vanillaRules, sampleSize)
}

fun runEmpiricalAnalysis(threshold: Int, ruleset: GameRuleset, sampleSize: Int) {
    var sumOfAllRounds = 0
    for (i in 1..sampleSize) {
        val game = GameInstance(threshold,ruleset)
        sumOfAllRounds += game.gameTurn()
    }
    val average : Double = sumOfAllRounds.toDouble()/sampleSize.toDouble()
    println(""+threshold+" "+average)
}

fun checkDice(ruleset: GameRuleset, sampleSize: Int) {
    val game = GameInstance(0,ruleset)
    var target = game.rollTheDice()
    var counter = 0
    for (i in 1..sampleSize) {
        val diceValue = game.rollTheDice()
        if(diceValue == target) counter ++
    }
    val frequency : Double = counter.toDouble()/sampleSize.toDouble()
    val probability : Double = 1/(ruleset.highestDiceValue - ruleset.lowestDiceValue + 1).toDouble()
    println("DICE-CHECK:: for die value: "+target+" frequency: "+frequency+" vs. probability "+probability)
}
