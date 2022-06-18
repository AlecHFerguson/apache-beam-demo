package ai.alec.deeppow.pipelines

import ai.alec.deeppow.beam.Avro.toAvro
import ai.alec.deeppow.beam.FilterElements.filter
import ai.alec.deeppow.beam.KPipe
import ai.alec.deeppow.beam.Map.map
import ai.alec.deeppow.beam.Text.fromText
import ai.alec.deeppow.beam.ParDoFunctions.parDo
import ai.alec.deeppow.dofn.KVPowerByDateTime
import ai.alec.deeppow.dofn.SumByACPower
import ai.alec.deeppow.model.toSolarReading
import ai.alec.deeppow.options.SolarProductionOptions
import org.apache.beam.sdk.transforms.GroupByKey

object SolarProductionPipeline {
    @JvmStatic
    fun main(args: Array<String>) {
        val (pipe, options) = KPipe.from<SolarProductionOptions>(args)
        val x = listOf<Int>(3)

        val validReadingKvs = pipe
            .fromText(
                name = "Read Solar Rows",
                filePath = options.inputFilePath
            )
            .map(name = "To SolarReading") {
                it.toSolarReading()
            }
            .filter(name = "Filter valid") {
                it.valid
            }
            .parDo(
                name = "KV Power by dateTime",
                doFn = KVPowerByDateTime()
            )

        validReadingKvs
            .apply(
                "Group By Key",
                GroupByKey.create<String, Double>()
            )
            .parDo(
                name = "Sum By AC Power",
                doFn = SumByACPower()
            )
            .toAvro(
                name = "Write Sums to Avro",
                filePath = options.outputFilePath
            )

        pipe.run()
    }
}