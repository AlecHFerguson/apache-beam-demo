package ai.alec.deeppow.options

import org.apache.beam.sdk.options.Description
import org.apache.beam.sdk.options.PipelineOptions
import org.apache.beam.sdk.options.ValueProvider

internal interface SolarProductionOptions : PipelineOptions {
    @get:Description("Cloud Storage path to solar data input files")
    var solarInputFilePath: ValueProvider<String>

    @get:Description("Cloud Storage path to weather data input files")
    var weatherInputFilePath: ValueProvider<String>

    @get:Description("Local path to output file")
    var outputFilePath: ValueProvider<String>
}