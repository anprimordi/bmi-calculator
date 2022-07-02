package com.bmi.calculator.data.local.dao

import androidx.room.*

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(data: List<T>): List<Long>

    @Update
    suspend fun update(data: T)

    @Delete
    suspend fun delete(data: T)

}