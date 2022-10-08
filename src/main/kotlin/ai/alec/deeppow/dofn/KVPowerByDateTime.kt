package ai.alec.deeppow.dofn

import ai.alec.deeppow.model.SolarReading
import org.apache.beam.sdk.transforms.DoFn
import org.apache.beam.sdk.values.KV

class KVPowerByDateTime : DoFn<SolarReading, KV<String, Double>>() {
    @ProcessElement
    fun processElement(context: ProcessContext) {
        val element = context.element()
        context.output(
            KV.of(
                element.dateTime,
                element.acPower
            )
        )
    }
}
