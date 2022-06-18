package ai.alec.deeppow.dofn

import ai.alec.deeppow.model.SolarSum
import com.winterbe.expekt.expect
import io.mockk.*
import org.apache.beam.sdk.transforms.DoFn
import org.apache.beam.sdk.values.KV
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object SumByACPowerSpec : Spek({
    describe("SumByACPowerTest") {
        context("For several measurements") {
            it("Outputs correct sum") {
                val subject = SumByACPower()

                val date = "2023-02-01 12:45:00"
                val readings = listOf(12.0, 13.0, 14.0, 15.0)
                val slot = slot<SolarSum>()
                val context = mockk<DoFn<KV<String, Iterable<@JvmWildcard Double>>, SolarSum>.ProcessContext> {
                    every { element() } returns KV.of(
                        date, readings
                    )
                    every { output(capture(slot)) } just Runs
                }
                subject.processElement(context)

                expect(slot.captured).to.equal(
                    SolarSum(dateTime = date, totalACPower = readings.sum())
                )
            }
        }
    }
})