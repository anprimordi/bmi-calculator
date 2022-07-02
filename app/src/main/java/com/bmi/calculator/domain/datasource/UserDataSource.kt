package com.bmi.calculator.domain.datasource

import com.bmi.calculator.domain.model.ScaleType
import com.bmi.calculator.domain.model.User
import com.bmi.calculator.domain.model.common.Result

interface UserDataSource {
    suspend fun checkHasSignedIn() : Result<Boolean?>
    suspend fun checkScaleType() : Result<ScaleType>
    suspend fun getUser() : Result<User>
    suspend fun saveUser(user: User) : Result<Unit>
    suspend fun saveScaleType(scaleType: ScaleType) : Result<Unit>
}