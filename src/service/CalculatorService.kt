package Service

import kotlin.math.pow

class CalculatorService {
    companion object {
        fun calculateSigma(resultList: MutableList<Int>, average: Double, sampleSize: Int): Double {
            var sumOfSquares: Double = 0.0
            for (i in 0 until sampleSize) {
                sumOfSquares += (resultList[i]-average).pow(2)
            }
            return (1/(sampleSize.toDouble()-1)).pow(0.5)
        }
    }
}