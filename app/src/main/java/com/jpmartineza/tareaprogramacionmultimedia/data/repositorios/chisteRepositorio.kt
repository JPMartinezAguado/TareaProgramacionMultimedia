package com.jpmartineza.tareaprogramacionmultimedia.data.repositorios

import android.util.Log
import com.jpmartineza.tareaprogramacionmultimedia.data.network.RetrofitClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class chisteRepositorio @Inject constructor(private val client: RetrofitClient) {
    suspend fun obtenerChisteAleatorio(): String {
        Log.d("chiste", "llega al repositorio")
        val chiste = client.service.obtenerChisteAleatorio().value
        return chiste
    }

}