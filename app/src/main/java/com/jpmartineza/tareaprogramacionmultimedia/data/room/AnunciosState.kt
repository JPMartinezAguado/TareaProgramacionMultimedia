package com.jpmartineza.tareaprogramacionmultimedia.data.room

import com.jpmartineza.tareaprogramacionmultimedia.R
import kotlinx.coroutines.flow.Flow


data class AnunciosState(
    val anuncios: List<Anuncios> = emptyList()
) {
    companion object {
       internal var _listadoAnuncios= mutableListOf(

            Anuncios(
                titulo = "Ayuda a mi perro",
                descripcion = "Ayuda a veterinariosc de tu zona a desparasitar perror callejeros",
                fechaPublicacion = "10/10/2023",
                fechaAccion = "10/10/2025",
                poblacion = "Alcorcón",
                tipo = "animales",
                imagen = R.drawable.perro
            ),
            Anuncios(
                titulo = "Ayuda a mi gato",
                descripcion = "Ayuda a veterinarios de tu zona a desparasitar gatos callejeros",
                fechaPublicacion = "10/10/2023",
                fechaAccion = "10/10/2025",
                poblacion = "Monzón",
                tipo = "animales",
                imagen = R.drawable.perro
            ),
            Anuncios(
                titulo = "Ayuda a mi conejo",
                descripcion = "Ayuda a veterinariosc de tu zona a desparasitar conejos callejeros",
                fechaPublicacion = "10/10/2023",
                fechaAccion = "10/10/2025",
                poblacion = "Lacón",
                tipo = "animales",
                imagen = R.drawable.perro
            ),
            Anuncios(
                titulo = "Compania a ancianos",
                descripcion = "Comparte tu tiempo en el centro de dia",
                fechaPublicacion = "10/10/2023",
                fechaAccion = "10/5/2025",
                poblacion = "Mondragon",
                tipo = "ancianos",
                imagen = R.drawable.anciano
            ),
            Anuncios(
                titulo = "Clases a inmigrantes",
                descripcion = "Comparte tu tiempo en el centro de acogida",
                fechaPublicacion = "10/10/2023",
                fechaAccion = "10/5/2025",
                poblacion = "Bentrances",
                tipo = "ancianos",
                imagen = R.drawable.anciano
            ),
            Anuncios(
                titulo = "Ayuda a mi perro",
                descripcion = "Ayuda a veterinariosc de tu zona a desparasitar perror callejeros",
                fechaPublicacion = "10/10/2023",
                fechaAccion = "10/10/2025",
                poblacion = "Madrid",
                tipo = "animales",
                imagen = R.drawable.perro
            ),
            Anuncios(
                titulo = "Colabora con el banco de alimentos",
                descripcion = "Recoger, clasificar y empaquetar alimentos en el banco de alimentos",
                fechaPublicacion = "10/10/2023",
                fechaAccion = "10/2/2025",
                poblacion = "Tarancon",
                tipo = "alimentos",
                imagen = R.drawable.alimentos
            ),
            Anuncios(
                titulo = "Atiende el comedor social",
                descripcion = "Cocinar, servir comida y limpiar el comedor",
                fechaPublicacion = "10/10/2023",
                fechaAccion = "10/4/2025",
                poblacion = "Barcelona",
                tipo = "comedor",
                imagen = R.drawable.comedorsocial
            ),
                    Anuncios(
                    titulo = "Ayuda a mi perro",
            descripcion = "Ayuda a veterinariosc de tu zona a desparasitar perror callejeros",
            fechaPublicacion = "10/10/2023",
            fechaAccion = "10/10/2025",
            poblacion = "Madrid",
            tipo = "animales",
            imagen = R.drawable.perro
        ),
        Anuncios(
        titulo = "Colabora con el banco de alimentos",
        descripcion = "Recoger, clasificar y empaquetar alimentos en el banco de alimentos",
        fechaPublicacion = "10/10/2023",
        fechaAccion = "10/2/2025",
        poblacion = "Tarancon",
        tipo = "alimentos",
        imagen = R.drawable.alimentos
        ),
        Anuncios(
        titulo = "Atiende el comedor social",
        descripcion = "Cocinar, servir comida y limpiar el comedor",
        fechaPublicacion = "10/10/2023",
        fechaAccion = "10/4/2025",
        poblacion = "Tui",
        tipo = "comedor",
        imagen = R.drawable.comedorsocial
        )
        )
        
        val listadoAnuncios: List<Anuncios>
        get() = _listadoAnuncios



        fun agregarAnuncio(anuncio: Anuncios) {
            _listadoAnuncios.add(anuncio)
        }

    }


}




