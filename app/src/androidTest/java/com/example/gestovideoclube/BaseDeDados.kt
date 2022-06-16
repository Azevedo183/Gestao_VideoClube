package com.example.gestovideoclube

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaseDeDados {
    private fun appContext() =
        InstrumentationRegistry.getInstrumentation().targetContext

    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = BDVideoClubeOpenHelper(appContext())
        return openHelper.writableDatabase
    }
    private fun insereCategoria(db: SQLiteDatabase, categoria: Categoria) {
        categoria.id = TabelaBDCategorias(db).insert(categoria.toContentValues())
        assertNotEquals(-1, categoria.id)
    }




}