package com.jpmartineza.tareaprogramacionmultimedia.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpmartineza.tareaprogramacionmultimedia.data.room.Anuncios
import com.jpmartineza.tareaprogramacionmultimedia.data.room.AnunciosDBDao
import com.jpmartineza.tareaprogramacionmultimedia.data.room.AnunciosState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnunciosViewModel @Inject constructor(
    private val dao: AnunciosDBDao
) : ViewModel() {
    private val _anunciosState = MutableStateFlow(AnunciosState())
    val anunciosState: StateFlow<AnunciosState>  get()= _anunciosState


    init {
        viewModelScope.launch {
            if (dao.obtenerCuentaAnuncios() == 0) {
                dao.insertarAnuncios(AnunciosState._listadoAnuncios)
            }
            actualizarAnuncios()
        }
    }

    fun insertarAnuncio(anuncio: Anuncios) = viewModelScope.launch {
        dao.insertarAnuncio(anuncio)
        actualizarAnuncios()
    }

    fun eliminarAnuncio(anuncio: Anuncios) = viewModelScope.launch {
        dao.eliminarAnuncio(anuncio)
        actualizarAnuncios()
    }

    fun actualizarAnuncios() = viewModelScope.launch {
        dao.obtenerAnuncios().collectLatest {
            anuncios -> _anunciosState.value = _anunciosState.value.copy(anuncios = anuncios)
        }
    }
}
