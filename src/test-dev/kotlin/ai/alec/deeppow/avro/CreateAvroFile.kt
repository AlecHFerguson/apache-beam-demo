package ai.alec.deeppow.avro

import ai.alec.deeppow.model.SolarReading
import ai.alec.deeppow.model.WeatherReading
import com.opencsv.bean.CsvToBeanBuilder
import org.apache.avro.file.DataFileWriter
import org.apache.avro.reflect.ReflectData
import org.apache.avro.reflect.ReflectDatumWriter
import java.io.File
import java.io.FileReader
import java.util.stream.Stream

fun main() {
    val inputPath = "/Users/alecferguson/scratch/solar-csv"
    val outputPath = "/Users/alecferguson/scratch/solar-avro"

    val solarReadingsOne = getReadings<SolarReading>("$inputPath/Plant_1_Generation_Data.csv")
    val solarReadingsTwo = getReadings<SolarReading>("$inputPath/Plant_2_Generation_Data.csv")
    writeAvro(
        filePath = "$outputPath/SolarGenerationData.avro",
        readingStream = Stream.concat(solarReadingsOne, solarReadingsTwo)
    )

    val weatherReadingsOne = getReadings<WeatherReading>("$inputPath/Plant_1_Weather_Sensor_Data.csv")
    val weatherReadingsTwo = getReadings<WeatherReading>("$inputPath/Plant_2_Weather_Sensor_Data.csv")
    writeAvro(
        filePath = "$outputPath/WeatherData.avro",
        readingStream = Stream.concat(weatherReadingsOne, weatherReadingsTwo)
    )
}

private inline fun <reified T> getReadings(filePath: String): Stream<T> {
    val fileReader = FileReader(filePath)
    return CsvToBeanBuilder<T>(fileReader)
        .withType(T::class.java)
        .build()
        .stream()
}

private inline fun <reified T> writeAvro(filePath: String, readingStream: Stream<T>) {
    val readingSchema = ReflectData.get().getSchema(T::class.java)
    val writer = ReflectDatumWriter(T::class.java)
    val avroFileWriter = DataFileWriter(writer)
    avroFileWriter.create(readingSchema, File(filePath))

    for (reading in readingStream) {
        avroFileWriter.append(reading)
    }
    avroFileWriter.close()
}
