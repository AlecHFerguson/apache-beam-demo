package ai.alec.deeppow.options;

import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;

public interface CustomPipelineOptions extends PipelineOptions {
    @Description("The Json spec")
    JsonSpec getJsonSpec();
    void setJsonSpec(JsonSpec jsonSpec);
}
