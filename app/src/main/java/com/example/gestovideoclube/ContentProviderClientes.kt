package com.example.gestovideoclube

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns


class ContentProviderClientes : ContentProvider() {
    var dbOpenHelper : BDVideoClubeOpenHelper? = null

    override fun onCreate(): Boolean {
        dbOpenHelper = BDVideoClubeOpenHelper(context)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val db = dbOpenHelper!!.readableDatabase

        requireNotNull(projection)
        val colunas = projection as Array<String>

        val argsSeleccao = selectionArgs as Array<String>?

        val id = uri.lastPathSegment

        val cursor = when (getUriMatcher().match(uri)) {
            URI_NOME -> TabelaBDCliente(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_NUMERO -> TabelaBDCliente(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_NOME_ESPECIFICO -> TabelaBDCliente(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"), null, null, null)
            URI_NUMERO_ESPECIFICO -> TabelaBDCliente(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"), null, null, null)
            else -> null
        }

        return cursor
    }

    override fun getType(uri: Uri): String? =
        when(getUriMatcher().match(uri)){
            URI_NOME -> "$MULTIPLOS_REGISTOS/${TabelaBDCliente.NOME}"
            URI_NUMERO -> "$MULTIPLOS_REGISTOS/${TabelaBDCliente.NOME}"
            URI_NOME_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDCliente.NOME}"
            URI_NUMERO_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDCliente.NOME}"
            else -> null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbOpenHelper!!.writableDatabase

        requireNotNull(values)

        val id = when (getUriMatcher().match(uri)){
            URI_NOME -> TabelaBDCliente(db).insert(values)
            URI_NUMERO -> TabelaBDCliente(db).insert(values)
            else -> -1
        }
        db.close()

        if(id == -1L) return null

        return Uri.withAppendedPath(uri, "$id")
    }


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = dbOpenHelper!!.writableDatabase

        val id = uri.lastPathSegment

        val registosApagados  = when (getUriMatcher().match(uri)) {
            URI_NOME_ESPECIFICO -> TabelaBDCliente(db).delete("${BaseColumns._ID}=?", arrayOf("$id"))
            URI_NUMERO_ESPECIFICO -> TabelaBDCliente(db).delete("${BaseColumns._ID}=?", arrayOf("$id"))
            else -> 0
        }
        db.close()

        return registosApagados
    }

    override fun update(uri: Uri,
                        values: ContentValues?,
                        selection: String?,
                        selectionArgs: Array<out String>?):
            Int {
        requireNotNull(values)

        val db = dbOpenHelper!!.writableDatabase

        val id = uri.lastPathSegment

        val registosAlterados = when (getUriMatcher().match(uri)) {
            URI_NOME_ESPECIFICO -> TabelaBDCliente(db).update(values, "${BaseColumns._ID}=?", arrayOf("$id"))
            URI_NUMERO_ESPECIFICO -> TabelaBDCliente(db).update(values,"${BaseColumns._ID}=?", arrayOf("$id"))
            else -> 0
        }
        return registosAlterados
    }

    companion object{
        private const val AUTURIDADE = "com.example.gestovideoclube"

        private const val URI_NOME = 100
        private const val URI_NOME_ESPECIFICO = 101
        private const val URI_NUMERO = 200
        private const val URI_NUMERO_ESPECIFICO = 201

        private const val UNICO_REGISTO = "vnd.android.cursor.item"
        private const val MULTIPLOS_REGISTOS = "vnd.android.cursor.dir"

        private val EDERECO_BASE = Uri.parse("content://$AUTURIDADE")
        val ENDERECO_NOME = Uri.withAppendedPath(EDERECO_BASE, TabelaBDCliente.NOME)
        val ENDERECO_NUM = Uri.withAppendedPath(EDERECO_BASE, TabelaBDCliente.NOME)

        private fun getUriMatcher(): UriMatcher {
            var uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher.addURI(AUTURIDADE, TabelaBDCliente.nome_cliente, URI_NOME)
            uriMatcher.addURI(AUTURIDADE, "${TabelaBDCliente.NOME}/#", URI_NOME_ESPECIFICO)
            uriMatcher.addURI(AUTURIDADE, TabelaBDCliente.nome_cliente, URI_NUMERO)
            uriMatcher.addURI(AUTURIDADE, "${TabelaBDCliente.NOME}/#", URI_NUMERO_ESPECIFICO)

            return  uriMatcher
        }
    }
}