package com.lightbreeze.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context


class LightBreezeApplication : Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context:Context

        const val KEY="7ed5e2f68bbb4d0bb55aa84801635088"
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
    }
}