package com.jpmartineza.tareaprogramacionmultimedia.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.jpmartineza.tareaprogramacionmultimedia.MainActivity

@Database(entities = [Anuncios::class], version = 1, exportSchema = false)

abstract class AnunciosDB: RoomDatabase(){
      abstract fun anunciosDao(): AnunciosDBDao

}