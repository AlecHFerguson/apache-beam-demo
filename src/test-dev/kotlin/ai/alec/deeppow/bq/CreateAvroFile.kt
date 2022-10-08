package ai.alec.deeppow.bq

import ai.alec.deeppow.model.SolarReading
import com.opencsv.bean.CsvToBeanBuilder
import org.apache.avro.file.DataFileWriter
import org.apache.avro.reflect.ReflectData
import org.apache.avro.reflect.ReflectDatumWriter
import org.apache.avro.specific.SpecificDatumWriter
import java.io.File
import java.io.FileReader

fun main() {
    val fileReader = FileReader("/Users/alecferguson/Downloads/Plant_1_Generation_Data.csv")
    val solarReadings = CsvToBeanBuilder<SolarReading>(fileReader)
        .withType(SolarReading::class.java)
        .build()
        .stream()

    val solarReadingSchema = ReflectData.get().getSchema(SolarReading::class.java)
    val solarReadingWriter = ReflectDatumWriter(SolarReading::class.java)
    val avroFileWriter = DataFileWriter(solarReadingWriter)
    avroFileWriter.create(solarReadingSchema, File("/Users/alecferguson/Downloads/GenerationData.avro"))

    for (solarReading in solarReadings) {
        avroFileWriter.append(solarReading)
    }
    avroFileWriter.close()
}
