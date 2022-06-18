package ai.alec.deeppow.exercises

import ai.alec.deeppow.model.SolarSum
import org.apache.avro.file.DataFileReader
import org.apache.avro.specific.SpecificDatumReader
import java.io.File

fun main() {
    val reader = SpecificDatumReader<SolarSum>(SolarSum::class.java)
    val file = File("/Users/alecferguson/scratch/dailies-00000-of-00003.avro")
    val dataFileReader = DataFileReader(file, reader)
    var sum: SolarSum? = null

    while (dataFileReader.hasNext()) {
        sum = dataFileReader.next(sum)
        println(sum)
    }
}
