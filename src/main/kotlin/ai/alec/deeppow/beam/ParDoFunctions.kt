package ai.alec.deeppow.beam

import org.apache.beam.sdk.transforms.DoFn
import org.apache.beam.sdk.transforms.ParDo
import org.apache.beam.sdk.transforms.SerializableFunction
import org.apache.beam.sdk.transforms.WithKeys
import org.apache.beam.sdk.values.KV
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

    fun <K, V> PCollection<V>.toKv(
        name: String,
        keyFunction: (V) -> K
    ): PCollection<KV<K, V>> {
        return apply(
            name,
            WithKeys.of(SerializableFunction<V, K> { keyFunction(it) })
        )
    }
}