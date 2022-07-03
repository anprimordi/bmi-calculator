package com.bmi.calculator.domain.datasource

import com.bmi.calculator.domain.model.Bmi
import com.bmi.calculator.domain.model.ScaleType
import com.bmi.calculator.domain.model.WeightCategory
import com.bmi.calculator.domain.model.common.Result

interface BmiDataSource {
    suspend fun countBmi(scaleType: ScaleType, weight: String, height: String): Result<Bmi>
    suspend fun getWeightCategory(bmi: Double) : Result<WeightCategory>
    suspend fun getBmiList(): Result<List<Bmi>>
    suspend fun getBmiById(id: Long): Result<Bmi>
    suspend fun saveBmi(bmi: Bmi): Result<Unit>
}