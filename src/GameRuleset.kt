class GameRuleset (var lowestDiceValue: Int, var highestDiceValue: Int, var goal: Int) {
    val dice = Dice(lowestDiceValue,highestDiceValue)
}