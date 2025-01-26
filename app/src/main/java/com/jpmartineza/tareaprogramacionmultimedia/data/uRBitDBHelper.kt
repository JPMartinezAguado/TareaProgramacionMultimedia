package com.jpmartineza.tareaprogramacionmultimedia.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.jpmartineza.tareaprogramacionmultimedia.data.uRBitDBContract.SQL_CREATE_ENTRIES
import com.jpmartineza.tareaprogramacionmultimedia.data.uRBitDBContract.SQL_DELETE_ENTRIES

class uRBitDBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun InsertarUsuario(usuario: String, contrasena: String, tipo: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(uRBitDBContract.Usuarios.COLUMN_NAME_NOMBRE, usuario)
            put(uRBitDBContract.Usuarios.COLUMN_NAME_CONNTRASENA, contrasena)
            put(uRBitDBContract.Usuarios.COLUMN_NAME_TIPO, tipo)
        }
        db.insert(uRBitDBContract.Usuarios.TABLE_NAME, null, values)
        db.close()
    }

    fun eliminarUsuario(usuario: String) {
        val db = this.writableDatabase
        db.delete(uRBitDBContract.Usuarios.TABLE_NAME, "${uRBitDBContract.Usuarios.COLUMN_NAME_NOMBRE} = ?", arrayOf(usuario))
        db.close()
    }

    fun actualizarUsuario(usuario: String, nuevoUsuario: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(uRBitDBContract.Usuarios.COLUMN_NAME_NOMBRE, nuevoUsuario)
        }
        db.update(uRBitDBContract.Usuarios.TABLE_NAME, values, "${uRBitDBContract.Usuarios.COLUMN_NAME_NOMBRE} = ?", arrayOf(usuario))
        db.close()
    }

    fun borrarBD() {
        val db = this.writableDatabase
        db.delete(uRBitDBContract.Usuarios.TABLE_NAME, null, null)
        db.close()
    }



}