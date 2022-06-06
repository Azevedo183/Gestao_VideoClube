package com.example.gestovideoclube

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDFilmes(db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $titulo_filme TEXT NOT NULL, $ano_filme INT NOT NULL, $duracao_filme INT NOT NULL)")
    }

    companion object {
        const val NOME = "filmes"
        const val titulo_filme = "titulo"
        const val ano_filme = "anoFilme"
        const val duracao_filme = "duracaoFilme"
    }
}