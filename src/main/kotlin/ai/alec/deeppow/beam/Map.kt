package ai.alec.deeppow.beam

import org.apache.beam.sdk.transforms.MapElements
import org.apache.beam.sdk.transforms.ProcessFunction
import org.apache.beam.sdk.values.PCollection
import org.apache.beam.sdk.values.TypeDescriptor

object Map {
    inline fun <In, reified Out> PCollection<In>.map(
        name: String = "Map",
        noinline transformer: (In) -> Out
    ): PCollection<Out> {
        return apply(
            name,
            MapElements.into(TypeDescriptor.of(Out::class.java))
                .via(ProcessFunction(transformer))
        )
    }
}