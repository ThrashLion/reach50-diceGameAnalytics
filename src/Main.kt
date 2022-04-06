import contantThreshold.EmpiricalAnalysis as ConstThresholdAnalysis
import contantThreshold.GameInstance as ConstThresholdGame

fun main() {
    val vanilla = GameRuleset(1, 6, 50)
    val sampleSize = 100000


    var constThresholdAnalysis = ConstThresholdAnalysis(vanilla, sampleSize)
    constThresholdAnalysis.runFullAnalysis()

    vanilla.dice.checkDice(sampleSize)
}
