package ai.alec.deeppow.model

import com.opencsv.bean.CsvBindByName
import java.io.Serializable

// DATE_TIME,PLANT_ID,SOURCE_KEY,DC_POWER,AC_POWER,DAILY_YIELD,TOTAL_YIELD
data class SolarReading(
    @CsvBindByName(column = "AC_POWER")
    val acPower: Double = Double.NaN,
    @CsvBindByName(column = "DAILY_YIELD")
    val dailyYield: Double = Double.NaN,
    @CsvBindByName(column = "DATE_TIME")
    val dateTime: String = "",
    @CsvBindByName(column = "DC_POWER")
    val dcPower: Double = Double.NaN,
    @CsvBindByName(column = "PLANT_ID")
    val plantId: String = "",
    @CsvBindByName(column = "SOURCE_KEY")
    val sourceKey: String = "",
    @CsvBindByName(column = "TOTAL_YIELD")
    val totalYield: Double = Double.NaN
//    val valid: Boolean = true
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
//            rawInput = this,
            sourceKey = splitRow[2],
            totalYield = splitRow[6].toDouble()
//            valid = true
        )
    } catch (e: IndexOutOfBoundsException) {
        return SolarReading(
//            invalidReason = e.toString(),
//            rawInput = this,
//            valid = false
        )
    } catch (e: NumberFormatException) {
        return SolarReading(
//            invalidReason = e.toString(),
//            rawInput = this,
//            valid = false
        )
    }
}

data class SolarSum(
    val totalACPower: Double = Double.NaN,
    val dateTime: String
) : Serializable
