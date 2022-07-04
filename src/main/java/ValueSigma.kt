import java.math.RoundingMode
import java.text.DecimalFormat

class ValueSigma (val value: Double, val sigma: Double) {

    private val decimalFormat = DecimalFormat("#.###")

    override fun toString (): String {
        return "("+ roundValue(value) +" +- "+ roundSigma(sigma) +")"
    }

    fun valueAsString(): String {
        return roundValue(value).toString()
    }

    fun sigmaAsString(): String {
        return roundSigma(sigma).toString()
    }

    private fun roundValue(value: Double): String {
        decimalFormat.roundingMode = RoundingMode.HALF_UP
        return decimalFormat.format(value)
    }

    private fun roundSigma(sigma: Double): String {
        decimalFormat.roundingMode = RoundingMode.UP
        return decimalFormat.format(sigma)
    }
}