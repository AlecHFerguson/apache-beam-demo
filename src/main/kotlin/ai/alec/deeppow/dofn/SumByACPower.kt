package ai.alec.deeppow.dofn

import ai.alec.deeppow.model.SolarSum
import org.apache.beam.sdk.transforms.DoFn
import org.apache.beam.sdk.values.KV

class SumByACPower : DoFn<KV<String, Iterable<@JvmWildcard Double>>, SolarSum>() {
    @ProcessElement
    fun processElement(context: ProcessContext) {
        val element = context.element()
        val dateTime = element.key
        val totalACPower = element.value.sum()

        context.output(
            SolarSum(
                totalACPower = totalACPower,
                dateTime = dateTime
            )
        )
    }
}