package com.jpmartineza.tareaprogramacionmultimedia

import android.app.Application
import android.content.res.Configuration
import android.util.Log

@HiltAndroidApp
class uR_BitApp : Application() {



    //tarea 2, parte 1, logs de rotar pantalla, cerar aplicacion, abrir aplicacion y poco memoria
    val TAG= "uR_BitApp"

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Aplicacion abierta")
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d(TAG, "pantalla rotada")

    }
    override fun onTerminate() {
        Log.d(TAG, "Aplicacion cerrada")
        super.onTerminate()
    }

    override fun onLowMemory() {
        Log.d(TAG, "queda poca memoria, quillo!")
        super.onLowMemory()
    }
}