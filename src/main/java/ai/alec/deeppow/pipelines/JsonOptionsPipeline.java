package ai.alec.deeppow.pipelines;

import ai.alec.deeppow.options.CustomPipelineOptions;
import ai.alec.deeppow.transforms.DummyTransform;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.ParDo;

import java.util.Arrays;

public class JsonOptionsPipeline {
    public static void main(String[] args) {
        final CustomPipelineOptions options = PipelineOptionsFactory
                .fromArgs(args)
                .as(CustomPipelineOptions.class);

        final Pipeline pipe = Pipeline.create(options);

        pipe.apply(
                Create.of(Arrays.asList(3))
        ).apply(
                ParDo.of(new DummyTransform(options.getJsonSpec()))
        );

        pipe.run();
    }
}
