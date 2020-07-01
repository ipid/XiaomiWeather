package me.ipid.android.xiaomiweather.logic.model

class WeatherInfoApiResponse {
    var status: String? = null
    var result: WeatherInfo? = null
}

class WeatherInfo {
    var realtime: RealtimeWeather? = null
}

class RealtimeWeather {
    var status: String? = null
    var skycon: String? = null
    var temperature: Float? = null
}
