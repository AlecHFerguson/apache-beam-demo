package ai.alec.deeppow.model

import org.apache.avro.reflect.Nullable
import java.io.Serializable

// DATE_TIME,PLANT_ID,SOURCE_KEY,DC_POWER,AC_POWER,DAILY_YIELD,TOTAL_YIELD
data class SolarReading(
    val acPower: Double = Double.NaN,
    val dailyYield: Double = Double.NaN,
    val dateTime: String = "",
    val dcPower: Double = Double.NaN,
    @get:Nullable
    val invalidReason: String? = null,
    @get:Nullable
    val plantId: String? = null,
    val rawInput: String = "",
    @get:Nullable
    val sourceKey: String? = null,
    val totalYield: Double = Double.NaN,
    val valid: Boolean = true
) : Serializable

fun String.toSolarReading(): SolarReading {
    val splitRow = split(",")
    try {
        return SolarReading(
            acPower = splitRow[4].toDouble(),
            dailyYield = splitRow[5].toDouble(),
            dateTime = splitRow[0],
            dcPower = splitRow[3].toDouble(),
            plantId = splitRow[1],
            rawInput = this,
            sourceKey = splitRow[2],
            totalYield = splitRow[6].toDouble(),
            valid = true
        )
    } catch (e: IndexOutOfBoundsException) {
        return SolarReading(
            invalidReason = e.toString(),
            rawInput = this,
            valid = false
        )
    } catch (e: NumberFormatException) {
        return SolarReading(
            invalidReason = e.toString(),
            rawInput = this,
            valid = false
        )
    }
}

data class SolarSum(
    val totalACPower: Double = Double.NaN,
    val dateTime: String
) : Serializable
