package com.jpmartineza.tareaprogramacionmultimedia.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpmartineza.tareaprogramacionmultimedia.data.network.RetrofitClient
import com.jpmartineza.tareaprogramacionmultimedia.data.network.ChisteService
import com.jpmartineza.tareaprogramacionmultimedia.domain.obtenerChisteUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChistakoViewModel @Inject constructor(private val obtenerChisteUserCase: obtenerChisteUserCase): ViewModel() {

    private val _chiste = MutableStateFlow("")
    val chiste: StateFlow<String> get() = _chiste

    fun obtenerChisteAleatorio() {
        viewModelScope.launch {
            Log.d("chiste", "Llamada a obtenerChisteAleatorio() iniciada")
           try {
                obtenerChisteUserCase.execute()
                Log.d("chiste", "Chiste obtenido: $chiste")

            } catch (e: Exception) {
                _chiste.value = "Error al obtener el chiste"
            }

        }

    }
}





