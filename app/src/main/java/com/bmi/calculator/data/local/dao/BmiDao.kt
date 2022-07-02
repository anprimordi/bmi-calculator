package com.bmi.calculator.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.bmi.calculator.data.local.entity.BmiEntity

@Dao
interface BmiDao : BaseDao<BmiEntity> {
    @Query(value = "SELECT * FROM bmi")
    suspend fun getBmiList(): List<BmiEntity>

    @Query(value = "SELECT * FROM bmi WHERE id = :bmiId")
    suspend fun getBmiById(bmiId: Long): BmiEntity
}