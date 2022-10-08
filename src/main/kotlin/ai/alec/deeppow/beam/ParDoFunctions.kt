package ai.alec.deeppow.beam

import org.apache.beam.sdk.coders.KvCoder
import org.apache.beam.sdk.coders.StringUtf8Coder
import org.apache.beam.sdk.transforms.DoFn
import org.apache.beam.sdk.transforms.ParDo
import org.apache.beam.sdk.transforms.SerializableFunction
import org.apache.beam.sdk.transforms.WithKeys
import org.apache.beam.sdk.values.KV
import org.apache.beam.sdk.values.PCollection
import org.apache.beam.sdk.values.PCollectionView
import kotlin.collections.Map

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

    fun <In, SideInputs, Out> PCollection<In>.parDo(
        name: String,
        doFn: DoFn<In, Out>,
        sideInputs: PCollectionView<Map<String, SideInputs>>
    ): PCollection<Out> {
        return this.apply(
            name,
            ParDo.of(doFn).withSideInputs(sideInputs)
        )
    }

    fun <V> PCollection<V>.toKv(
        name: String,
        keyFunction: (V) -> String
    ): PCollection<KV<String, V>> {
        return apply(
            name,
            WithKeys.of(SerializableFunction<V, String> { keyFunction(it) })
        ).setCoder(
            KvCoder.of(
                StringUtf8Coder.of(),
                coder
            )
        )
    }
}
