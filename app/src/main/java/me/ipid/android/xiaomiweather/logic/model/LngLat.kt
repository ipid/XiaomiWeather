package me.ipid.android.xiaomiweather.logic.model

data class LngLat(val lng: Float, val lat: Float) {
    constructor(lng: String, lat: String): this(lng.toFloat(), lat.toFloat())
    constructor(lng: Double, lat: Double): this(lng.toFloat(), lat.toFloat())

    override fun toString() = "$lng,$lat"
}
