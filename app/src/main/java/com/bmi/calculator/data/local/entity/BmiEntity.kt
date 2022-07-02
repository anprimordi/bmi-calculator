package com.bmi.calculator.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bmi.calculator.domain.model.Bmi

@Entity(tableName = "bmi")
data class BmiEntity(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var bmi: Double,
    var weight: Double,
    var height: Double,
    var bodyType: String,
    var timestamp: Long
) {
    companion object {
        fun fromDomain(model: Bmi) : BmiEntity {
            return BmiEntity(
                id = model.id ?: 0,
                bmi = model.bmi,
                weight = model.weight,
                height = model.height,
                bodyType = model.bodyType,
                timestamp = model.timestamp
            )
        }
    }

    fun toDomain() : Bmi{
        return Bmi(
            id = id,
            bmi = bmi,
            weight = weight,
            height = height,
            bodyType = bodyType,
            timestamp = timestamp
        )
    }
}