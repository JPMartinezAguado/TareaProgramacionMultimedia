package com.jpmartineza.tareaprogramacionmultimedia.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anuncios")
class Anuncios(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("titulo") val titulo: String,
    @ColumnInfo("descripcion") val descripcion: String,
    @ColumnInfo("fechaPublicacion") val fechaPublicacion: String,
    @ColumnInfo("fechaAccion") val fechaAccion: String,
    @ColumnInfo("poblacion") val poblacion: String,
    @ColumnInfo("tipo") val tipo: String,
    @ColumnInfo("imagen") val imagen: Int,
    )
