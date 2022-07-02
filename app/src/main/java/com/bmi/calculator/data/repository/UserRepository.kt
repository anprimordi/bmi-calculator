package com.bmi.calculator.data.repository

import com.bmi.calculator.domain.datasource.UserDataSource
import com.bmi.calculator.domain.model.ScaleType
import com.bmi.calculator.domain.model.User
import com.bmi.calculator.domain.model.common.Result
import com.bmi.calculator.domain.model.common.Success
import com.krakatio.aplikasiconvertpulsa.di.datasource.LocalDataSource
import javax.inject.Inject

class UserRepository @Inject constructor(
    @LocalDataSource private val localDataSource: UserDataSource,
) : UserDataSource {
    override suspend fun checkHasSignedIn(): Result<Boolean?> {
//        return localDataSource.checkHasSignedIn()
        return Success(true)
    }

    override suspend fun checkScaleType(): Result<ScaleType> {
        return localDataSource.checkScaleType()
    }

    override suspend fun getUser(): Result<User> {
        return localDataSource.getUser()
    }

    override suspend fun saveUser(user: User): Result<Unit> {
        return localDataSource.saveUser(user)
    }

    override suspend fun saveScaleType(scaleType: ScaleType): Result<Unit> {
        return localDataSource.saveScaleType(scaleType)
    }

}