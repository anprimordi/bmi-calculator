package com.bmi.calculator.data.remote.model.response

import com.bmi.calculator.data.remote.util.RemoteDateTimeUtils
import com.bmi.calculator.domain.model.Bmi
import com.google.gson.annotations.SerializedName

data class BmiResponse(
    @SerializedName(value = "bmi") val bmi: Double? = null,
    @SerializedName(value = "weight") val weight: String? = null,
    @SerializedName(value = "height") val height: String? = null,
    @SerializedName(value = "weightCategory") val weightCategory: String? = null,
) {
    fun toDomain() : Bmi {
        return Bmi(
            id = null,
            bmi = bmi ?: 0.0,
            weight = weight?.toDouble() ?: 0.0,
            height = height?.toDouble() ?: 0.0,
            bodyType = weightCategory ?: "",
            timestamp = RemoteDateTimeUtils.getCurrentDate()
        )
    }
}
