package ai.alec.deeppow.transforms;

import ai.alec.deeppow.options.JsonSpec;
import org.apache.beam.sdk.transforms.DoFn;

public class DummyTransform extends DoFn<Integer, Integer> {
    private JsonSpec jsons;

    public DummyTransform(JsonSpec jsonSpec) {
        jsons = jsonSpec;
    }

    @ProcessElement
    public void processElement(ProcessContext context) {
        context.output(context.element());
    }
}
