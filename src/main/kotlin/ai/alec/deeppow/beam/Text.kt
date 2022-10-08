package ai.alec.deeppow.beam

import org.apache.beam.sdk.Pipeline
import org.apache.beam.sdk.io.TextIO
import org.apache.beam.sdk.options.ValueProvider
import org.apache.beam.sdk.values.PCollection

object Text {
    fun Pipeline.fromText(
        name: String = "From Text",
        filePath: ValueProvider<String>
    ): PCollection<String> {
        return apply(
            name,
            TextIO.read().from(filePath)
        )
    }
}
