package ai.alec.deeppow.dofn;

import ai.alec.deeppow.model.JavaReading;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

public class JavaKVByPower extends DoFn<JavaReading, KV<String, Double>> {
    @ProcessElement
    void processElement(ProcessContext context) {
        final JavaReading element = context.element();
        context.output(
                KV.of(
                        element.getDateTime(), element.getAcPower()
                )
        );
    }
}
