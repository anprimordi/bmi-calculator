package com.krakatio.aplikasiconvertpulsa.di.datasource

import com.bmi.calculator.data.local.source.BmiLocalDataSource
import com.bmi.calculator.data.local.source.UserLocalDataSource
import com.bmi.calculator.domain.datasource.BmiDataSource
import com.bmi.calculator.domain.datasource.UserDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    @LocalDataSource
    abstract fun bindBmiDataSource(bmiLocalDataSource: BmiLocalDataSource): BmiDataSource

    @Binds
    @LocalDataSource
    abstract fun bindUserDataSource(userLocalDataSource: UserLocalDataSource): UserDataSource

}