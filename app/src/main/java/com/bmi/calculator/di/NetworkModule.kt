package com.bmi.calculator.di

import com.bmi.calculator.data.remote.AppRemoteClient
import com.bmi.calculator.data.remote.service.BmiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBmiService(appRemoteClient: AppRemoteClient): BmiService {
        return appRemoteClient.create(BmiService::class.java)
    }

}