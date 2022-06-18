package ai.alec.deeppow.options;

import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.ValueProvider;

public interface SolarJavaOptions extends PipelineOptions {
    @Description("Absolute path to input file on the file system")
    ValueProvider<String> getInputFilePath();
    void setInputFilePath();
}
