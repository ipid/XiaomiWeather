package me.ipid.android.xiaomiweather.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.ipid.android.xiaomiweather.logic.network.caiyunService
import me.ipid.android.xiaomiweather.publicContext

private const val TAG = "MainActivityViewModel"

class MainActivityViewModel : ViewModel() {

    val temperature = MutableLiveData<String>()

    private val locMan: LocationManager by lazy {
        publicContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    private fun providerChooser(): String? {
        val allProvider = locMan.getProviders(true)

        for (i in listOf(
            LocationManager.NETWORK_PROVIDER,
            LocationManager.GPS_PROVIDER,
            LocationManager.PASSIVE_PROVIDER
        )) {
            if (i in allProvider) {
                return i
            }
        }

        return allProvider.getOrNull(0)
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(): Location? {
        val provider = providerChooser()
        if (provider == null) {
            Log.d(TAG, "getLocation: 位置提供器为 null")
            return null
        }

        return locMan.getLastKnownLocation(provider)
    }

    fun updateWeather(): LiveData<Unit> {
        val ld = MutableLiveData<Unit>()

        viewModelScope.launch {
            // 获取位置、提取经纬度
            Log.d(TAG, "updateWeather: 正在获取位置")
            val loc = getLocation()
            if (loc == null) {
                Log.d(TAG, "updateWeather: 获取到的位置是 null")
                return@launch
            }
            val lng = "%.3f".format(loc.longitude)
            val lat = "%.3f".format(loc.latitude)

            // 获取天气信息
            val weather = caiyunService.getWeather("$lng,$lat")
            Log.d(TAG, "updateWeather: 已获取到天气")

            // 更新天气文本
            Log.d(TAG, "updateWeather: ${Thread.currentThread().name}")
            val temperatureText = weather.result?.realtime?.temperature?.toString() ?: return@launch
            temperature.value = temperatureText
        }

        return ld
    }
}
