package me.ipid.android.xiaomiweather.logic.network

import me.ipid.android.xiaomiweather.logic.model.WeatherInfoApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CaiyunService {
    @GET("{lngLat}/weather.json")
    suspend fun getWeather(@Path("lngLat") lngLat: String): WeatherInfoApiResponse
}
