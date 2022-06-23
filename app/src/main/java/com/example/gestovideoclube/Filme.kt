package com.example.gestovideoclube

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Filme(
    var titulo: String,
    var ano: String,
    var duracao: String,
    var categoria: Categoria,
    var id: Long = -1
    ) {
        fun toContentValues() : ContentValues {
            val valores = ContentValues()

            valores.put(TabelaBDFilmes.titulo_filme, titulo)
            valores.put(TabelaBDFilmes.ano_filme, ano)
            valores.put(TabelaBDFilmes.duracao_filme, duracao)
            valores.put(TabelaBDFilmes.categoria_id, categoria.id)

            return valores
        }

        companion object {
            fun fromCursor(cursor: Cursor) : Filme {
                val posId = cursor.getColumnIndex(BaseColumns._ID)
                val posTitulo = cursor.getColumnIndex(TabelaBDFilmes.titulo_filme)
                val posAno = cursor.getColumnIndex(TabelaBDFilmes.ano_filme)
                val posDuracao = cursor.getColumnIndex(TabelaBDFilmes.duracao_filme)
                val posIdCateg = cursor.getColumnIndex(TabelaBDFilmes.categoria_id)
                val posNomeCateg = cursor.getColumnIndex(TabelaBDCategorias.CAMPO_NOME)

                val id = cursor.getLong(posId)
                val titulo = cursor.getString(posTitulo)
                val ano = cursor.getString(posAno)
                val duracao = cursor.getString(posDuracao)
                val idCateg = cursor.getLong(posIdCateg)
                val nomeCat = cursor.getString(posNomeCateg)

                val categoria = Categoria(nomeCat, idCateg)

                return Filme(titulo, ano, duracao, categoria, id)
            }
        }
    }
