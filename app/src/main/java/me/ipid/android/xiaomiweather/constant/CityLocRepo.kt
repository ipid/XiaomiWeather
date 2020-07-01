package me.ipid.android.xiaomiweather.constant

import android.content.Context
import me.ipid.android.xiaomiweather.MyApplication
import me.ipid.android.xiaomiweather.R
import me.ipid.android.xiaomiweather.publicContext
import org.apache.commons.lang3.StringUtils
import java.io.BufferedReader
import java.io.InputStreamReader

data class CityLoc(val keyword: String, val lng: String, val lat: String)

val cityLocs: List<CityLoc> by lazy {
    readCityLocRepo(publicContext)
}

private fun readCityLocRepo(context: Context): List<CityLoc> {
    val reader = BufferedReader(
        InputStreamReader(
            context.resources.openRawResource(R.raw.city_loc_repo)
        ), 512 * 1024
    )

    val citys = ArrayList<CityLoc>()
    while (true) {
        val line: String? = reader.readLine()
        if (line == null) {
            break
        }

        val splitResult = StringUtils.split(line, ',')
        citys.add(CityLoc(splitResult[0], splitResult[1], splitResult[2]))
    }

    return citys
}
