package com.jpmartineza.tareaprogramacionmultimedia.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AnunciosDBDao {

    @Query("SELECT COUNT(*) FROM anuncios")
    suspend fun obtenerCuentaAnuncios(): Int

    @Query("SELECT * FROM anuncios")
    fun obtenerAnuncios(): Flow<List<Anuncios>>

    @Query("SELECT * FROM anuncios ORDER BY fechaPublicacion DESC")
    fun obtenerAnunciosPorFecha(): Flow<List<Anuncios>>

    @Query("SELECT * FROM anuncios WHERE tipo = :tipo")
    fun obtenerAnunciosPorTipo(tipo: String): Flow<List<Anuncios>>

    @Query("SELECT * FROM anuncios WHERE poblacion = :poblacion")
    fun obtenerAnunciosPorPoblacion(poblacion: String): Flow<List<Anuncios>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAnuncio(anuncio: Anuncios)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAnuncios(anuncios: List<Anuncios>)

    @Update
    suspend fun actualizarAnuncio(anuncio: Anuncios)

    @Delete
    suspend fun eliminarAnuncio(anuncio: Anuncios)



}