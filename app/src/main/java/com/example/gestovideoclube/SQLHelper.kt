package com.example.gestovideoclube

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.example.gestovideoclube.SQLHelper
import android.database.sqlite.SQLiteDatabase

class SQLHelper(private val context: Context?) : SQLiteOpenHelper(
    context, db_name, null, db_version
) {
    override fun onCreate(db: SQLiteDatabase) {
        val query = "CREATE TABLE " + tb_name_filmes +
                " (" + id_filme + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                titulo_filme + " TEXT, " +
                categorias_filme + " TEXT, " +
                tempo_filme + " INTEGER, " +
                ano_filme + " INTEGER);"
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + tb_name_filmes)
        onCreate(db)
    }

    companion object {
        private const val db_name = "Videoclube.db"
        private const val db_version = 1
        private const val tb_name_filmes = "filmes"
        private const val id_filme = "_id"
        private const val titulo_filme = "titulo_filme"
        private const val categorias_filme = "categorias_filmes"
        private const val tempo_filme = "_tempo_filme"
        private const val ano_filme = "_ano_filme"
    }
}