package com.bmi.calculator.data.remote.source

import com.bmi.calculator.data.remote.service.BmiService
import com.bmi.calculator.data.remote.util.execute
import com.bmi.calculator.domain.datasource.BmiDataSource
import com.bmi.calculator.domain.model.Bmi
import com.bmi.calculator.domain.model.ScaleType
import com.bmi.calculator.domain.model.WeightCategory
import com.bmi.calculator.domain.model.common.Error
import com.bmi.calculator.domain.model.common.Result
import com.bmi.calculator.domain.model.common.Success
import com.bmi.calculator.domain.model.common.UnsupportedError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BmiRemoteDataSource @Inject constructor(
    private val bmiService: BmiService
) : BmiDataSource {
    override suspend fun countBmi(
        scaleType: ScaleType,
        weight: String,
        height: String
    ): Result<Bmi> {
        return withContext(Dispatchers.IO) {
            execute {
                val weights = weight.toDouble()
                val heights = height.toDouble()

                if (scaleType == ScaleType.METRIC) {
                    bmiService.countBmiMetric(weights, heights)
                } else {
                    bmiService.countBmiImperial(weights, heights)
                }
            } mapTo {
                if (it != null) Success(it.toDomain())
                else Error.general()
            }
        }
    }

    override suspend fun getWeightCategory(bmi: Double): Result<WeightCategory> {
        return withContext(Dispatchers.IO) {
            execute {
                bmiService.getWeightCategory(bmi)
            } mapTo {
                if (it != null) Success(it.toDomain())
                else Error.general()
            }
        }
    }

    override suspend fun getBmiList(): Result<List<Bmi>> {
        return UnsupportedError(source = this)
    }

    override suspend fun getBmiById(id: Long): Result<Bmi> {
        return UnsupportedError(source = this)
    }

    override suspend fun saveBmi(bmi: Bmi): Result<Unit> {
        return UnsupportedError(source = this)
    }
}