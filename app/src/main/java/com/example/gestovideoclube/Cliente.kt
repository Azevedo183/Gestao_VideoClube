package com.example.gestovideoclube

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Filmes(

    var nome: String,
    var numero_telefone: String,
    var id: Long = -1
) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDCliente.nome_cliente, nome)
        valores.put(TabelaBDCliente.cliente_numero, numero_telefone)

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor) : Filmes {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDCliente.nome_cliente)
            val posNumero = cursor.getColumnIndex(TabelaBDCliente.cliente_numero)


            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val numero = cursor.getString(posNumero)

            return Filmes(nome, numero, id)
        }
    }
}
