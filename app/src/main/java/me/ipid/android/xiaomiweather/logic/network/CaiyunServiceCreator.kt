package me.ipid.android.xiaomiweather.logic.network

import me.ipid.android.xiaomiweather.constant.caiyunToken
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val caiyunService by lazy {
    Retrofit.Builder()
        .baseUrl("https://api.caiyunapp.com/v2.5/$caiyunToken/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CaiyunService::class.java)
}
