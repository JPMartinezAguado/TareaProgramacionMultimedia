package com.jpmartineza.tareaprogramacionmultimedia.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AnunciosDBDao {

    @Query("SELECT * FROM anuncios")
    fun obtenerAnuncios(): Flow<List<Anuncios>>

    @Query("SELECT * FROM anuncios ORDER BY fechaPublicacion DESC")
    fun obtenerAnunciosPorFecha(): Flow<List<Anuncios>>

    @Query("SELECT * FROM anuncios WHERE tipo = :tipo")
    fun obtenerAnunciosPorTipo(tipo: String): Flow<List<Anuncios>>

    @Query("SELECT * FROM anuncios WHERE poblacion = :poblacion")
    fun obtenerAnunciosPorPoblacion(poblacion: String): Flow<List<Anuncios>>

    @Insert
    suspend fun insertarAnuncio(anuncio: Anuncios)

    @Update
    suspend fun actualizarAnuncio(anuncio: Anuncios)

    @Delete
    suspend fun eliminarAnuncio(anuncio: Anuncios)



}