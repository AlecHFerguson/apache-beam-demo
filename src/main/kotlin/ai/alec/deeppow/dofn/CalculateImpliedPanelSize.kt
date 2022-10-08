package ai.alec.deeppow.dofn

import ai.alec.deeppow.model.ImpliedPanelSize
import ai.alec.deeppow.model.SolarReading
import ai.alec.deeppow.model.WeatherReading
import ai.alec.deeppow.model.getWeatherKey
import org.apache.beam.sdk.transforms.DoFn
import org.apache.beam.sdk.values.PCollectionView

const val panelEfficiency = 0.18

class CalculateImpliedPanelSize(private val weatherView: PCollectionView<Map<String, WeatherReading>>) : DoFn<SolarReading, ImpliedPanelSize>() {
    @ProcessElement
    fun processElement(@Element element: SolarReading, context: ProcessContext) {
        val weatherKey = getWeatherKey(plantId = element.plantId, dateTime = element.dateTime)
        val weatherReading: WeatherReading? = context.sideInput(weatherView).get(weatherKey)
        if (weatherReading != null) {
            context.output(
                ImpliedPanelSize(
                    plantId = element.plantId,
                    sourceKey = element.sourceKey,
                    dateTime= element.dateTime,
                    dcPower = element.dcPower,
                    irradiation = weatherReading.irradiation,
                    impliedPanelSize = calcImpliedPanelSize(dcPower = element.dcPower, irradiation = weatherReading.irradiation)
                )
            )
        }
    }

    private fun calcImpliedPanelSize(dcPower: Double, irradiation: Double): Double {
        return dcPower / (irradiation * panelEfficiency)
    }
}