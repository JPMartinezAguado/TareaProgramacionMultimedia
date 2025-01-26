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
            dao.obtenerAnuncios().collectLatest() {
                state = state.copy(
                    anuncios = it
                )
            }
        }
    }

    fun insertarAnuncio(anuncio: Anuncios) = viewModelScope.launch {
        dao.insertarAnuncio(anuncio = anuncio)

    }

    fun eliminarAnuncio(anuncio: Anuncios) = viewModelScope.launch {
        dao.eliminarAnuncio(anuncio = anuncio)
    }

    fun actualizarAnuncio(anuncio: Anuncios) = viewModelScope.launch {
        dao.actualizarAnuncio(anuncio = anuncio)
    }


}
