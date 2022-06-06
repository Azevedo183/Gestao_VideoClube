package com.example.gestovideoclube

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class BDVideoClubeOpenHelper(context: Context?) : SQLiteOpenHelper(context, NOME, null, VERSAO) {

    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)

        TabelaBDCategorias(db).cria()
        TabelaBDFilmes(db).cria()
        TabelaBDCliente(db).cria()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        const val NOME = "VideoClube.db"
        private const val VERSAO = 1
    }
}