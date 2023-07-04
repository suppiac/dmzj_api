package com.example.dmzj_api.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RClient {
    fun init(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun init(url: String, factory: ScalarsConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(factory)
            .build()
    }
}