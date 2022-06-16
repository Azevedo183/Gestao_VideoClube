package com.example.gestovideoclube

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDCliente(db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $nome_cliente TEXT NOT NULL, $cliente_numero INT NOT NULL)")
    }

    companion object {
        const val NOME = "cliente"
        const val nome_cliente = "nome"
        const val cliente_numero = "numeroCliente"
    }
}