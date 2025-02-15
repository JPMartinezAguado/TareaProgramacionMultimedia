package com.jpmartineza.tareaprogramacionmultimedia.domain

import android.util.Log
import com.jpmartineza.tareaprogramacionmultimedia.data.repositorios.chisteRepositorio
import javax.inject.Inject

class obtenerChisteUserCase @Inject constructor(private val repositorio: chisteRepositorio) {
    suspend fun execute(): String {
        Log.d("chiste", "Ejecutando UserCase")
        val chiste = repositorio.obtenerChisteAleatorio()
        Log.d("chiste", "Chiste obtenido: $chiste")
        return chiste
    }
}
