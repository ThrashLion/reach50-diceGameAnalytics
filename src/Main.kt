fun main() {
    val vanillaRules = GameRuleset(1,6,50)
    val threshold = 20
    val game = GameInstance(threshold,vanillaRules)

    println("testing dice: "+game.rollTheDice())
    println("threshold: "+threshold+" rounds:"+game.gameTurn())
}
