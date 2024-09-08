package com.sourabh.webkool_assignment.di

import com.sourabh.webkool_assignment.api.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(ApiInterface.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)
