package ai.alec.deeppow.pipelines;

import ai.alec.deeppow.dofn.JavaKVByPower;
import ai.alec.deeppow.dofn.JavaSumACPower;
import ai.alec.deeppow.model.JavaReading;
import ai.alec.deeppow.options.SolarJavaOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.GroupByKey;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.TypeDescriptor;

public class SolarProductionJava {
    public static void main(String[] args) {
        SolarJavaOptions options = PipelineOptionsFactory.fromArgs(args)
                .withValidation()
                .as(SolarJavaOptions.class);

        Pipeline pipe = Pipeline.create(options);

        pipe
                .apply(
                        "From text",
                        TextIO.read().from(options.getInputFilePath())
                )
                .apply(
                        "To SolarReading",
                        MapElements.into(TypeDescriptor.of(JavaReading.class))
                                .via(
                                        (String message) -> JavaReading.parseFromString(message)
                                )
                )
                .apply(
                        "Filter Valid",
                        Filter.by((JavaReading::isValid))
                )
                .apply(
                        "KV Power by DateTime",
                        ParDo.of(new JavaKVByPower())
                )
                .apply(
                        "Group By Key",
                        GroupByKey.<String, Double>create()
                )
                .apply(
                        "Sum By AC Power",
                        ParDo.of(new JavaSumACPower())
                )
        ;

        pipe.run();
    }
}

