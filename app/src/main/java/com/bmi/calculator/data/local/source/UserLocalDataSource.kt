package com.bmi.calculator.data.local.source

import com.bmi.calculator.data.local.AppPreference
import com.bmi.calculator.domain.datasource.UserDataSource
import com.bmi.calculator.domain.model.ScaleType
import com.bmi.calculator.domain.model.User
import com.bmi.calculator.domain.model.common.AuthError
import com.bmi.calculator.domain.model.common.Error
import com.bmi.calculator.domain.model.common.Result
import com.bmi.calculator.domain.model.common.Success
import com.google.gson.Gson
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val preference: AppPreference
) : UserDataSource {

    private val gson = Gson()

    override suspend fun checkHasSignedIn(): Result<Boolean?> {
        return try {
            val signedIn = preference.get().getBoolean(AppPreference.KEY_SIGNED_IN, false)
            Success(signedIn)
        } catch (ex: Exception) {
            ex.printStackTrace()
            AuthError()
        }
    }

    override suspend fun checkScaleType(): Result<ScaleType> {
        return try {
            val scale = preference.get().getString(AppPreference.KEY_SCALE_TYPE, null) ?: throw Exception()
            if (scale == ScaleType.METRIC.name) {
                Success(ScaleType.METRIC)
            } else {
                Success(ScaleType.IMPERIAL)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Error.construct(ex)
        }
    }

    override suspend fun getUser(): Result<User> {
        return try {
            val userJson = preference.get().getString(AppPreference.KEY_USER, null) ?: throw Exception()
            val user = gson.fromJson(userJson, User::class.java) ?: throw Exception()
            Success(user)
        } catch (ex: Exception) {
            ex.printStackTrace()
            AuthError()
        }
    }

    override suspend fun saveUser(user: User): Result<Unit> {
        return try {
            val userJson = gson.toJson(user) ?: throw Exception()
            val isSaved = preference.editor()
                .putString(AppPreference.KEY_USER, userJson)
                .commit()
            if (isSaved) Success(Unit) else Error.unknown()
        } catch (ex: Exception) {
            ex.printStackTrace()
            Error.construct(ex)
        }
    }

    override suspend fun saveScaleType(scaleType: ScaleType): Result<Unit> {
        return try {
            val scale = scaleType.name
            val isSaved = preference.editor().putString(AppPreference.KEY_SCALE_TYPE, scale).commit()
            if (isSaved) Success(Unit) else Error.unknown()
        } catch (ex: Exception) {
            ex.printStackTrace()
            Error.construct(ex)
        }
    }

}