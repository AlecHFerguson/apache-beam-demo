package ai.alec.deeppow.options

import org.apache.beam.sdk.options.Description
import org.apache.beam.sdk.options.PipelineOptions
import org.apache.beam.sdk.options.ValueProvider

internal interface SolarProductionOptions : PipelineOptions {
    @get:Description("Local path to input file")
    var inputFilePath: ValueProvider<String>

    @get:Description("Local path to output file")
    var outputFilePath: ValueProvider<String>
}