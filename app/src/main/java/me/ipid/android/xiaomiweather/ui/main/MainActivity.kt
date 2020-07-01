package me.ipid.android.xiaomiweather.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import me.ipid.android.xiaomiweather.R

private const val TAG = "MainActivity"
const val REQ_LOCATION_PERMISSION = 1

class MainActivity : AppCompatActivity() {

    lateinit var vm: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideWindowStatusBar()
        registerViewModel()
        beginUpdateWeather()
    }

    private fun hideWindowStatusBar() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
    }

    private fun registerViewModel() {
        vm = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        vm.temperature.observe(this, Observer {
            text_main_temperature.text = it
        })
    }

    private fun afterGeoLocation() {
        vm.updateWeather()
    }

    private fun beginUpdateWeather() {
        // 检查权限
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            afterGeoLocation()
            return
        }

        // 请求权限
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQ_LOCATION_PERMISSION
        )
        Log.d(TAG, "beginUpdateWeather: 已请求权限")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQ_LOCATION_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onRequestPermissionsResult: 已获取权限，正在更新天气")
            afterGeoLocation()
        }
    }
}
