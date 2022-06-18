package ai.alec.deeppow.exercises

import ai.alec.deeppow.model.SolarReading
import org.apache.avro.reflect.ReflectData


fun main() {
    val schema = ReflectData.get().getSchema(SolarReading::class.java)
    println(schema)
}