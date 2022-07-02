package com.bmi.calculator.data.local.util

import kotlin.math.pow

object ScaleConverter {

    fun kgToPounds(value: Double) : Double = value * 2.205

    fun poundToKg(value: Double) : Double = value / 2.205

    fun meterToInch(value: Double) : Double = value * 39.37

    fun inchToMeter(value: Double) : Double = value / 39.37

    fun countBmiMetric(weight: Double, height: Double) : Double {
        return weight / height.pow(2)
    }

    fun countBmiImperial(weight: Double, height: Double) : Double {
        return weight / height * 703
    }
}