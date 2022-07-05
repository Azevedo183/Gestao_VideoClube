package com.example.gestovideoclube

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDCliente(db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $nome_cliente TEXT NOT NULL, $cliente_numero INT NOT NULL)")
    }

    override fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor? {
        return super.query(columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    companion object {
        const val NOME = "cliente"
        const val nome_cliente = "nome"
        const val cliente_numero = "numeroCliente"
        const val CAMPO_ID = "${NOME}.${BaseColumns._ID}"

        val TODAS_COLUNAS = arrayOf(CAMPO_ID, nome_cliente, cliente_numero)
    }
}
