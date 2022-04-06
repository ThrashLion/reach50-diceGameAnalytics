import contantThreshold.GameInstance

class Dice (val lowestDiceValue:Int, val highestDiceValue:Int) {
    fun roll(): Int {
        return (lowestDiceValue until (highestDiceValue+1)).random()
    }

    fun checkDice(sampleSize: Int) {
        var target = roll()
        var counter = 0
        for (i in 1..sampleSize) {
            val diceValue = roll()
            if(diceValue == target) counter ++
        }
        val frequency : Double = counter.toDouble()/sampleSize.toDouble()
        val probability : Double = 1/(highestDiceValue - lowestDiceValue + 1).toDouble()
        println("DICE-CHECK:: for die value: "+target+" frequency: "+frequency+" vs. probability "+probability)
    }
}