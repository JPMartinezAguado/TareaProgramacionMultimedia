package com.jpmartineza.tareaprogramacionmultimedia.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AnunciosViewModel(
    private val dao: AnunciosDBDao
) : ViewModel() {
    var state by mutableStateOf(AnunciosState())
        private set

    init {
        viewModelScope.launch {
            dao.obtenerAnuncios().collect() { anuncios ->
                state = state.copy(
                    anuncios = anuncios
                )
            }
        }
    }

    fun agregarAnuncio(anuncio: Anuncios) = viewModelScope.launch {
        dao.insertarAnuncio(anuncio)

    }

    fun eliminarAnuncio(anuncio: Anuncios) = viewModelScope.launch {
        dao.eliminarAnuncio(anuncio)
    }

    fun actualizarAnuncio(anuncio: Anuncios) = viewModelScope.launch {
        dao.actualizarAnuncio(anuncio)
    }


}
