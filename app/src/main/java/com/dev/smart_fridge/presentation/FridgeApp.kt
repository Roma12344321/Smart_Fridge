package com.dev.smart_fridge.presentation

import android.app.Application
import com.dev.smart_fridge.di.DaggerApplicationComponent

class FridgeApp : Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}