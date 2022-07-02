package com.bmi.calculator.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bmi.calculator.BuildConfig
import com.bmi.calculator.data.local.dao.BmiDao
import com.bmi.calculator.data.local.entity.BmiEntity

@Database(
    version = BuildConfig.DB_VERSION,
    exportSchema = false,
    entities = [
        BmiEntity::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var sInstance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return sInstance ?: synchronized(this) {
                sInstance ?: buildDatabase(context).also { sInstance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                BuildConfig.DB_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }

    abstract fun bmiDao(): BmiDao

}