package ai.alec.deeppow.model

import com.opencsv.bean.CsvBindByName

// DATE_TIME,PLANT_ID,SOURCE_KEY,AMBIENT_TEMPERATURE,MODULE_TEMPERATURE,IRRADIATION
data class WeatherReading(
    @CsvBindByName(column = "DATE_TIME")
    val dateTime: String,
    @CsvBindByName(column = "PLANT_ID")
    val plantId: String,
    @CsvBindByName(column = "SOURCE_KEY")
    val sourceKey: String,
    @CsvBindByName(column = "AMBIENT_TEMPERATURE")
    val ambientTemperature: Double,
    @CsvBindByName(column = "MODULE_TEMPERATURE")
    val moduleTemperature: Double,
    @CsvBindByName(column = "IRRADIATION")
    val irradiation: Double
)

fun getWeatherKey(plantId: String, dateTime: String): String = "$dateTime:$plantId"
