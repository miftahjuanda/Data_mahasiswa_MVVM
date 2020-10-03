package com.udacoding.mahasiswa_app_miftah.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    fun getRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl("http://192.168.1.7/mentoring_kotlin_week4/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    fun service() : ApiService = getRetrofit().create(ApiService::class.java)
}