package com.krakatio.aplikasiconvertpulsa.di.datasource

import com.bmi.calculator.data.repository.BmiRepository
import com.bmi.calculator.data.repository.UserRepository
import com.bmi.calculator.domain.datasource.BmiDataSource
import com.bmi.calculator.domain.datasource.UserDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
annotation class Repository

@Qualifier
annotation class LocalDataSource

@Qualifier
annotation class RemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Repository
    abstract fun bindBmiDataSource(bmiRepository: BmiRepository): BmiDataSource

    @Binds
    @Repository
    abstract fun bindUserDataSource(userRepository: UserRepository): UserDataSource

}