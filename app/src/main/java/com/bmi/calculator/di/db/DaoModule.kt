package com.krakatio.aplikasiconvertpulsa.di.db

import com.bmi.calculator.data.local.AppDatabase
import com.bmi.calculator.data.local.dao.BmiDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun provideProviderDao(appDatabase: AppDatabase): BmiDao {
        return appDatabase.bmiDao()
    }

}