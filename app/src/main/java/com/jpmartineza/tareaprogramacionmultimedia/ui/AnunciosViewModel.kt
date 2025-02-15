package com.jpmartineza.tareaprogramacionmultimedia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jpmartineza.tareaprogramacionmultimedia.data.repositorios.AnunciossRepositorio
import com.jpmartineza.tareaprogramacionmultimedia.data.room.Anuncios
import com.jpmartineza.tareaprogramacionmultimedia.data.room.AnunciosDBDao
import com.jpmartineza.tareaprogramacionmultimedia.data.room.AnunciosState
import com.jpmartineza.tareaprogramacionmultimedia.domain.AddAnuncioUserCase
import com.jpmartineza.tareaprogramacionmultimedia.domain.GetAnunciosUserCase
import com.jpmartineza.tareaprogramacionmultimedia.domain.RemoveAnuncioUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnunciosViewModel @Inject constructor(
    private val anunciossRepositorio: AnunciossRepositorio,
    private val dao: AnunciosDBDao
) : ViewModel() {

    val anuncios: LiveData<List<Anuncios>> = dao.obtenerTodosAnuncios().asLiveData()


        fun insertarAnuncio(anuncio: Anuncios) {
        viewModelScope.launch {
            anunciossRepositorio.addAnuncio(anuncio)
        }

    }

    fun eliminarAnuncio(anuncio: Anuncios) {
        viewModelScope.launch {
            anunciossRepositorio.removeAnuncio(anuncio)
        }
    }

    private val _anunciosState = MutableStateFlow(AnunciosState())
    val anunciosState: StateFlow<AnunciosState>  get()= _anunciosState


    init {
        viewModelScope.launch {
           if (dao.obtenerCuentaAnuncios() == 0) {
                dao.insertarAnuncios(anunciossRepositorio._listadoAnuncios)}
            anunciossRepositorio.anuncios.collect { anuncios ->
               _anunciosState.value = AnunciosState(anuncios)
            //actualizarAnuncios()
        }
    }


//    fun insertarAnuncio(anuncio: Anuncios) = viewModelScope.launch {
//        AddAnuncioUserCase(AnunciossRepositorio())
//    }
//
//    fun eliminarAnuncio(anuncio: Anuncios) = viewModelScope.launch {
//        RemoveAnuncioUserCase(AnunciossRepositorio())
//    }

    //fun actualizarAnuncios() = viewModelScope.launch {
    //    dao.obtenerAnuncios().collectLatest {
     //       anuncios -> _anunciosState.value = _anunciosState.value.copy(anuncios = anuncios)
    //    }
    }
}
