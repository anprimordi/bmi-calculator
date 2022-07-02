package com.bmi.calculator.data.remote.model.response

import com.bmi.calculator.domain.model.WeightCategory
import com.google.gson.annotations.SerializedName

data class WeightCategoryResponse(
    @SerializedName(value = "bmi") val bmi: String? = null,
    @SerializedName(value = "weightCategory") val weightCategory: String? = null
) {
    fun toDomain() : WeightCategory {
        return WeightCategory(
            bmi = bmi?.toDouble() ?: 0.0,
            weightCategory = weightCategory ?: ""
        )
    }
}