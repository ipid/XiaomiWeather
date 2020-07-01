package me.ipid.android.xiaomiweather.constant

import me.ipid.android.xiaomiweather.MyApplication
import me.ipid.android.xiaomiweather.R
import me.ipid.android.xiaomiweather.publicContext
import java.util.*

val caiyunToken: String by lazy {
    Properties()
        .apply { load(publicContext.resources.openRawResource(R.raw.token)) }
        .getProperty("token")
}
