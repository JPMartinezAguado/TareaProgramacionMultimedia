package com.jpmartineza.tareaprogramacionmultimedia.domain

import com.jpmartineza.tareaprogramacionmultimedia.data.repositorios.AnunciossRepositorio
import com.jpmartineza.tareaprogramacionmultimedia.data.room.Anuncios
import javax.inject.Inject


    class RemoveAnuncioUserCase @Inject constructor(
        private val anunciosRepositorio: AnunciossRepositorio
    ) {
        suspend operator fun invoke(anuncio: Anuncios) {
            anunciosRepositorio.removeAnuncio(anuncio)
        }
    }

