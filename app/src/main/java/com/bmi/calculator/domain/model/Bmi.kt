package com.bmi.calculator.domain.model

data class Bmi(
    val id: Long?,
    val bmi: Double,
    val weight: Double,
    val height: Double,
    val bodyType: String,
    val timestamp: Long
)
