package ai.alec.deeppow.dofn;

import ai.alec.deeppow.model.JavaSum;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

public class JavaSumACPower extends DoFn<KV<String, Iterable<Double>>, JavaSum> {
    @ProcessElement
    void processElement(ProcessContext context) {
        final KV<String, Iterable<Double>> element = context.element();
        final String dateTime = element.getKey();
        final Iterable<Double> acPowers = element.getValue();

        final double totalACPower = sumTotalACPower(acPowers);

        context.output(
                JavaSum.newBuilder()
                        .setDateTime(dateTime)
                        .setTotalACPower(totalACPower)
                        .build()
        );
    }

    private double sumTotalACPower(Iterable<Double> values) {
        double sum = 0.0;
        for (double value: values) {
            sum += value;
        }
        return sum;
    }
}
