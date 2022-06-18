package ai.alec.deeppow.beam

import org.apache.beam.sdk.transforms.Filter
import org.apache.beam.sdk.transforms.SerializableFunction
import org.apache.beam.sdk.values.PCollection

object FilterElements {
    fun <In> PCollection<In>.filter(
        name: String = "Map",
        predicate: (In) -> Boolean
    ): PCollection<In> {
        return apply(
            name,
            Filter.by(SerializableFunction<In, Boolean>(predicate))
        )
    }
}