package ai.alec.deeppow.beam

import ai.alec.deeppow.beam.FilterElements.filter
import org.apache.beam.sdk.testing.PAssert
import org.apache.beam.sdk.testing.TestPipeline
import org.apache.beam.sdk.transforms.Create
import org.junit.Rule
import org.junit.Test
import java.io.Serializable

private data class Snow(
    val temperature: Double = Double.NaN,
    val heightOfSnow: Double = Double.NaN
) : Serializable

class FilterElementsSpec {
    @get:Rule
    val pipeline: TestPipeline = TestPipeline.create()

    @Test
    fun testElementsFiltered() {
        val awesomeSnow = Snow(temperature = -20.3, heightOfSnow = 40.4)
        val testReadings = listOf(awesomeSnow, Snow(temperature = -3.2, heightOfSnow = 3.2))

        val outputs = pipeline.apply(Create.of(testReadings))
            .filter(name = "Filter awesome snow") {
                it.heightOfSnow > 30.3 && it.temperature < -10.1
            }
        PAssert.that<Snow>(outputs)
            .containsInAnyOrder(Snow(temperature = -3.2, heightOfSnow = 3.2))

        pipeline.run()
    }
}
