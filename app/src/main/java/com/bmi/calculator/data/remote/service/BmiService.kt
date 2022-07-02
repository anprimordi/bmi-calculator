package com.bmi.calculator.data.remote.service

import com.bmi.calculator.data.remote.model.response.BmiResponse
import com.bmi.calculator.data.remote.model.response.WeightCategoryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BmiService {

    @GET(value = "metric")
    suspend fun countBmiMetric(
        @Query(value = "weight") weight: Double,
        @Query(value = "height") height: Double
    ) : BmiResponse

    @GET(value = "imperial")
    suspend fun countBmiImperial(
        @Query(value = "weight") weight: Double,
        @Query(value = "height") height: Double
    ) : BmiResponse

    @GET(value = "weight-category")
    suspend fun getWeightCategory(
        @Query(value = "bmi") bmi: Double,
    ) : WeightCategoryResponse
}