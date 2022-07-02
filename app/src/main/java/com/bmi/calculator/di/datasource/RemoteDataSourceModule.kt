package com.krakatio.aplikasiconvertpulsa.di.datasource

import com.bmi.calculator.data.remote.source.BmiRemoteDataSource
import com.bmi.calculator.domain.datasource.BmiDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    @RemoteDataSource
    abstract fun bindBmiDataSource(bmiRemoteDataSource: BmiRemoteDataSource): BmiDataSource

}