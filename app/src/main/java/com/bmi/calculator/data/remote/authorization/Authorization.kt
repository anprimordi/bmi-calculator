package com.bmi.calculator.data.remote.authorization

import okhttp3.Interceptor
import okhttp3.Response

class Authorization(private val key: String, private val host: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .header(name = "X-RapidAPI-Key", value = key)
                .header(name = "X-RapidAPI-Host", value = host)
                .build()
        )
    }

}