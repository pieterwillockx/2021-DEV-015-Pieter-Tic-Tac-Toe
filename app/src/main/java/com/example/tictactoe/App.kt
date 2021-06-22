package com.example.tictactoe

import android.app.Application
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/poppins_black.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build())
    }
}