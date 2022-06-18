package ai.alec.deeppow.beam

import org.apache.beam.sdk.transforms.DoFn
import org.apache.beam.sdk.transforms.ParDo
import org.apache.beam.sdk.values.PCollection

object ParDoFunctions {
    fun <In, Out> PCollection<In>.parDo(
        name: String,
        doFn: DoFn<In, Out>
    ): PCollection<Out> {
        return this.apply(
            name,
            ParDo.of(doFn)
        )
    }
}