import contantThreshold.EmpiricalAnalysis as ConstThresholdAnalysis
import adaptingThreshold.EmpiricalAnalysis as AdaptThresholdAnalysis

fun main() {
    val vanilla = GameRuleset(1, 6, 50)
    val sampleSize = 1000000

    val constThresholdActive: Boolean = true
    val adaptThresholdActive: Boolean = false

    vanilla.dice.checkDice(sampleSize)

    if (constThresholdActive) {
        var constThresholdAnalysis = ConstThresholdAnalysis(vanilla, sampleSize)
        constThresholdAnalysis.runFullAnalysis()
    }
    if (adaptThresholdActive) {
        var adaptThresholdAnalysis = AdaptThresholdAnalysis(vanilla, sampleSize)
        adaptThresholdAnalysis.runFullAnalysis()
    }

}
