package ai.alec.deeppow.model

import com.winterbe.expekt.expect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object SolarReadingSpec : Spek({
    describe("SolarReading") {
        describe("String#toSolarReading()") {
            context("For valid reading") {
                it("SolarReading is populated") {
                    val testReading = "2023-04-20 12:30:15,kale,cruciferous,12.3,11.2,123.4,9876"
                    val reading = testReading.toSolarReading()
                    expect(reading).to.equal(
                        SolarReading(
                            acPower = 11.2,
                            dailyYield = 123.4,
                            dateTime = "2023-04-20 12:30:15",
                            dcPower = 12.3,
                            plantId = "kale",
//                            rawInput = testReading,
                            sourceKey = "cruciferous",
                            totalYield = 9876.0
                        )
                    )
                }
            }

            context("For invalid readings") {
                it("Missing field => error shown") {
                    val testReading = "2023-04-20 12:30:15,kale,cruciferous,12.3,11.2,123.4"
                    val reading = testReading.toSolarReading()
                    expect(reading).to.equal(
                        SolarReading(
//                            invalidReason = "java.lang.IndexOutOfBoundsException: Index 6 out of bounds for length 6",
//                            rawInput = testReading,
//                            valid = false
                        )
                    )
                }

                it("Non-double field => error shown") {
                    val testReading = "2023-04-20 12:30:15,kale,cruciferous,cauliflower,11.2,123.4,9876"
                    val reading = testReading.toSolarReading()
                    expect(reading).to.equal(
                        SolarReading(
//                            invalidReason = "java.lang.NumberFormatException: For input string: \"cauliflower\"",
//                            rawInput = testReading,
//                            valid = false
                        )
                    )
                }
            }
        }
    }
})
