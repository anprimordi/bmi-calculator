package com.bmi.calculator.data.repository

import com.bmi.calculator.domain.datasource.BmiDataSource
import com.bmi.calculator.domain.model.Bmi
import com.bmi.calculator.domain.model.ScaleType
import com.bmi.calculator.domain.model.WeightCategory
import com.bmi.calculator.domain.model.common.Error
import com.bmi.calculator.domain.model.common.NetworkError
import com.bmi.calculator.domain.model.common.Result
import com.krakatio.aplikasiconvertpulsa.di.datasource.LocalDataSource
import com.krakatio.aplikasiconvertpulsa.di.datasource.RemoteDataSource
import javax.inject.Inject

class BmiRepository @Inject constructor(
    @LocalDataSource private val localDataSource: BmiDataSource,
    @RemoteDataSource private val remoteDataSource: BmiDataSource
) : BmiDataSource {
    override suspend fun countBmi(
        scaleType: ScaleType,
        weight: String,
        height: String
    ): Result<Bmi> {
        return remoteDataSource.countBmi(scaleType, weight, height)
    }

    override suspend fun getWeightCategory(bmi: Double): Result<WeightCategory> {
        return remoteDataSource.getWeightCategory(bmi)
    }

    override suspend fun getBmiList(): Result<List<Bmi>> {
        return localDataSource.getBmiList()
    }

    override suspend fun getBmiById(id: Long): Result<Bmi> {
        return localDataSource.getBmiById(id)
    }

    override suspend fun saveBmi(bmi: Bmi): Result<Unit> {
        return localDataSource.saveBmi(bmi)
    }
}