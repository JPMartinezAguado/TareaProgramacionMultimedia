package com.jpmartineza.tareaprogramacionmultimedia.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Anuncios::class], version = 1)

abstract class AnunciosDB: RoomDatabase(){
      abstract fun anunciosDao(): AnunciosDBDao

}