package com.example.gestovideoclube

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns


class ContentProviderFilmes : ContentProvider() {
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
            URI_TITULO -> TabelaBDFilmes(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_ANO -> TabelaBDFilmes(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_TITULO_ESPECIFICO -> TabelaBDFilmes(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"), null, null, null)
            URI_ANO_ESPECIFICO -> TabelaBDFilmes(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"), null, null, null)
            URI_DURACAO -> TabelaBDFilmes(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_DURACAO_ESPECIFICO -> TabelaBDFilmes(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"), null, null, null)
            else -> null
        }

        return cursor
    }

    override fun getType(uri: Uri): String? =
        when(getUriMatcher().match(uri)){
            URI_TITULO -> "$MULTIPLOS_REGISTOS/${TabelaBDFilmes.NOME}"
            URI_ANO -> "$MULTIPLOS_REGISTOS/${TabelaBDFilmes.NOME}"
            URI_DURACAO -> "$MULTIPLOS_REGISTOS/${TabelaBDFilmes.NOME}"
            URI_TITULO_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDFilmes.NOME}"
            URI_ANO_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDFilmes.NOME}"
            URI_DURACAO_ESPECIFICO-> "$UNICO_REGISTO/${TabelaBDFilmes.NOME}"
            else -> null
        }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbOpenHelper!!.writableDatabase

        requireNotNull(values)

        val id = when (getUriMatcher().match(uri)){
            URI_TITULO -> TabelaBDFilmes(db).insert(values)
            URI_ANO -> TabelaBDFilmes(db).insert(values)
            URI_DURACAO -> TabelaBDFilmes(db).insert(values)
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
            URI_TITULO_ESPECIFICO -> TabelaBDFilmes(db).delete("${BaseColumns._ID}=?", arrayOf("$id"))
            URI_ANO_ESPECIFICO -> TabelaBDFilmes(db).delete("${BaseColumns._ID}=?", arrayOf("$id"))
            URI_DURACAO_ESPECIFICO -> TabelaBDFilmes(db).delete("${BaseColumns._ID}=?", arrayOf("$id"))
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
            URI_TITULO_ESPECIFICO -> TabelaBDFilmes(db).update(values, "${BaseColumns._ID}=?", arrayOf("$id"))
            URI_ANO_ESPECIFICO -> TabelaBDFilmes(db).update(values,"${BaseColumns._ID}=?", arrayOf("$id"))
            Companion.URI_DURACAO_ESPECIFICO -> TabelaBDFilmes(db).update(values,"${BaseColumns._ID}=?", arrayOf("$id"))
            else -> 0
        }
        return registosAlterados
    }

    companion object{
        private const val AUTURIDADE = "com.example.gestovideoclube"

        private const val URI_TITULO = 100
        private const val URI_TITULO_ESPECIFICO = 101
        private const val URI_ANO = 200
        private const val URI_ANO_ESPECIFICO = 201
        private const val URI_DURACAO = 300
        private const val URI_DURACAO_ESPECIFICO = 301

        private const val UNICO_REGISTO = "vnd.android.cursor.item"
        private const val MULTIPLOS_REGISTOS = "vnd.android.cursor.dir"

        private val EDERECO_BASE = Uri.parse("content://$AUTURIDADE")
        val ENDERECO_NOME = Uri.withAppendedPath(EDERECO_BASE, TabelaBDFilmes.NOME)
        val ENDERECO_NUM = Uri.withAppendedPath(EDERECO_BASE, TabelaBDCliente.NOME)

        private fun getUriMatcher(): UriMatcher {
            var uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher.addURI(AUTURIDADE, TabelaBDFilmes.titulo_filme, URI_TITULO)
            uriMatcher.addURI(AUTURIDADE, "${TabelaBDFilmes.NOME}/#", URI_TITULO_ESPECIFICO)
            uriMatcher.addURI(AUTURIDADE, TabelaBDFilmes.ano_filme, URI_ANO)
            uriMatcher.addURI(AUTURIDADE, "${TabelaBDFilmes.NOME}/#", URI_ANO_ESPECIFICO)
            uriMatcher.addURI(AUTURIDADE, TabelaBDFilmes.duracao_filme, URI_DURACAO)
            uriMatcher.addURI(AUTURIDADE, "${TabelaBDFilmes.NOME}/#", URI_DURACAO_ESPECIFICO)
            return  uriMatcher
        }
    }
}