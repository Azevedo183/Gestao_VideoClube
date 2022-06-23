package com.example.gestovideoclube

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDFilmes(db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $titulo_filme TEXT NOT NULL, $ano_filme INT NOT NULL, $duracao_filme INT NOT NULL,$categoria_id INT NOT NULL, FOREIGN KEY ($categoria_id) REFERENCES ${TabelaBDCategorias.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    companion object {
        const val NOME = "filmes"
        const val titulo_filme = "titulo"
        const val ano_filme = "anoFilme"
        const val duracao_filme = "duracaoFilme"
        const val  categoria_id = "categoriaId"
        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"

        val TODAS_COLUNAS = arrayOf(CAMPO_ID, titulo_filme, ano_filme, duracao_filme, categoria_id, TabelaBDCategorias.CAMPO_NOME)
    }
}