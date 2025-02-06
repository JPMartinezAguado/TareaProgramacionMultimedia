package com.jpmartineza.tareaprogramacionmultimedia.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpmartineza.tareaprogramacionmultimedia.data.network.RetrofitClient
import com.jpmartineza.tareaprogramacionmultimedia.data.network.ChisteService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChistakoViewModel @Inject constructor(private val client: RetrofitClient): ViewModel() {

    private val _chiste = MutableStateFlow("...se viene chistaco...")
    val chiste: StateFlow<String> get() = _chiste

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    fun obtenerChisteAleatorio() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val chisteResponse = client.service.obtenerChisteAleatorio()
                _chiste.value = chisteResponse.value
            } catch (e: Exception) {
                _chiste.value = "Error al obtener el chiste"
                Log.e("ChistakoViewModel", "Error al obtener el chiste", e)
            }
            _loading.value = false
        }
        }
    }




