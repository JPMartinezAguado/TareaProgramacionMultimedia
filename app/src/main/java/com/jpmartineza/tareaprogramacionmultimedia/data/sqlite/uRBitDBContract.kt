package com.jpmartineza.tareaprogramacionmultimedia.data.sqlite

import android.provider.BaseColumns

const val DATABASE_NAME = "uRBit.db"
const val DATABASE_VERSION = 1

object uRBitDBContract {

    object Usuarios : BaseColumns {
        const val TABLE_NAME = "Usuarios"
        const val COLUMN_NAME_NOMBRE = "nombre"
        const val COLUMN_NAME_CONNTRASENA = "contrasena"
        const val COLUMN_NAME_TIPO = "tipo"
    }


    const val SQL_CREATE_ENTRIES = "CREATE TABLE ${Usuarios.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${Usuarios.COLUMN_NAME_NOMBRE} TEXT," +
            "${Usuarios.COLUMN_NAME_CONNTRASENA} TEXT," +
            "${Usuarios.COLUMN_NAME_TIPO} TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${Usuarios.TABLE_NAME}"

}