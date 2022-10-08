package ai.alec.deeppow.pipelines

import ai.alec.deeppow.beam.Avro.fromAvroClass
import ai.alec.deeppow.beam.Avro.toAvro
import ai.alec.deeppow.beam.KPipe
import ai.alec.deeppow.beam.ParDoFunctions.parDo
import ai.alec.deeppow.beam.ParDoFunctions.toKv
import ai.alec.deeppow.dofn.CalculateImpliedPanelSize
import ai.alec.deeppow.model.SolarReading
import ai.alec.deeppow.model.WeatherReading
import ai.alec.deeppow.model.getWeatherKey
import ai.alec.deeppow.options.SolarProductionOptions
import org.apache.beam.sdk.transforms.View

object SolarProductionPipeline {
    @JvmStatic
    fun main(args: Array<String>) {
        val (pipeline, options) = KPipe.from<SolarProductionOptions>(args)

        val solarReadings = pipeline
            .fromAvroClass<SolarReading>(filePath = options.solarInputFilePath)

        val weatherReadingsMap = pipeline
            .fromAvroClass<WeatherReading>(filePath = options.weatherInputFilePath)
            .toKv(name = "KV by PlantID Datetime") {
                getWeatherKey(plantId = it.plantId, dateTime = it.dateTime)
            }
            .apply(View.asMap())

        solarReadings
            .parDo(
                name = "Get Implied Panel Size",
                doFn = CalculateImpliedPanelSize(weatherView = weatherReadingsMap),
                sideInputs = weatherReadingsMap
            )
            .toAvro(name = "Implied sizes to Avro", filePath = options.outputFilePath)

        pipeline.run()
    }
}
