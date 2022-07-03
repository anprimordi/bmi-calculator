package com.bmi.calculator.data.local.source

import com.bmi.calculator.data.local.dao.BmiDao
import com.bmi.calculator.data.local.entity.BmiEntity
import com.bmi.calculator.data.local.util.ScaleConverter
import com.bmi.calculator.data.remote.util.RemoteDateTimeUtils
import com.bmi.calculator.domain.datasource.BmiDataSource
import com.bmi.calculator.domain.model.Bmi
import com.bmi.calculator.domain.model.BodyType
import com.bmi.calculator.domain.model.ScaleType
import com.bmi.calculator.domain.model.WeightCategory
import com.bmi.calculator.domain.model.common.Error
import com.bmi.calculator.domain.model.common.Result
import com.bmi.calculator.domain.model.common.Success
import com.bmi.calculator.domain.model.common.UnsupportedError
import java.util.*
import javax.inject.Inject

class BmiLocalDataSource @Inject constructor(
    private val bmiDao: BmiDao
) : BmiDataSource {
    override suspend fun countBmi(scaleType: ScaleType, weight: String, height: String): Result<Bmi> {
        return try {
            val bmi: Double
            val weights = weight.toDouble()
            val heights = height.toDouble()

            bmi = if (scaleType == ScaleType.IMPERIAL) {
                ScaleConverter.countBmiImperial(weights, heights)
            } else {
                ScaleConverter.countBmiMetric(weights, heights)
            }
            val bodyType = when {
                bmi >= 30 -> "Obese"
                bmi >= 25 -> "Overweight"
                bmi >= 19 -> "Normal Weight"
                bmi < 19 -> "Underweight"
                else -> ""
            }

            Success(Bmi(id = Random().nextLong(), bmi = bmi, weight = weights, height = heights, bodyType = bodyType, timestamp = RemoteDateTimeUtils.getCurrentDate()))
        } catch (ex: Exception) {
            ex.printStackTrace()
            Error.construct(ex)
        }
    }

    override suspend fun getWeightCategory(bmi: Double): Result<WeightCategory> {
        return UnsupportedError(source = this)
    }

    override suspend fun getBmiList(): Result<List<Bmi>> {
        return try {
            val data = bmiDao.getBmiList().map { it.toDomain() }
            Success(data)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Error.construct(ex)
        }
    }

    override suspend fun getBmiById(id: Long): Result<Bmi> {
        return try {
            val data = bmiDao.getBmiById(id).toDomain()
            Success(data)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Error.construct(ex)
        }
    }

    override suspend fun saveBmi(bmi: Bmi): Result<Unit> {
        return try {
            bmiDao.insert(BmiEntity.fromDomain(bmi))
            Success(Unit)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Error.construct(ex)
        }
    }

}