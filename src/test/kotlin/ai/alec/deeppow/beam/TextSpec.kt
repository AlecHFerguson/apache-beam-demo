package ai.alec.deeppow.beam

import ai.alec.deeppow.beam.Text.fromText
import com.winterbe.expekt.expect
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.apache.beam.sdk.Pipeline
import org.apache.beam.sdk.io.TextIO
import org.apache.beam.sdk.options.ValueProvider
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object TextSpec : Spek({
    describe("Text") {
        describe("Pipeline.fromText()") {
            it("TextIO transform is passed to Pipeline") {
                val nameSlot = slot<String>()
                val textIOSlot = slot<TextIO.Read>()
                val pipeline = mockk<Pipeline> {
                    every { apply(capture(nameSlot), capture(textIOSlot)) } returns mockk()
                }

                val name = "Read Somethin'"
                val filePath = ValueProvider.StaticValueProvider.of("/best/skintrack/ever.csv")
                pipeline.fromText(
                    name = name,
                    filePath = filePath
                )

                expect(nameSlot.captured).to.equal(name)
                expect(textIOSlot.captured).to.equal(
                    TextIO.read().from(filePath)
                )
            }
        }
    }
})
