package com.bmi.calculator.data.repository

import com.bmi.calculator.domain.datasource.BmiDataSource
import com.bmi.calculator.domain.model.Bmi
import com.bmi.calculator.domain.model.ScaleType
import com.bmi.calculator.domain.model.common.Result
import com.bmi.calculator.domain.model.common.Success
import com.krakatio.aplikasiconvertpulsa.di.datasource.LocalDataSource
import com.krakatio.aplikasiconvertpulsa.di.datasource.RemoteDataSource
import java.util.*
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
        val bmi = Bmi(
            id = 0,
            bmi = 20.2,
            weight = 70.0,
            height = 1.83,
            bodyType = "Normal",
            timestamp = Date().time
        )
        return Success(bmi)
//        return remoteDataSource.countBmi(scaleType, weight, height)
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