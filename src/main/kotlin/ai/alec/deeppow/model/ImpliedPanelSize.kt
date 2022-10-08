package ai.alec.deeppow.model

import java.io.Serializable

data class ImpliedPanelSize(
    val plantId: String,
    val sourceKey: String,
    val dateTime: String,
    val dcPower: Double,
    val irradiation: Double,
    val impliedPanelSize: Double
) : Serializable
