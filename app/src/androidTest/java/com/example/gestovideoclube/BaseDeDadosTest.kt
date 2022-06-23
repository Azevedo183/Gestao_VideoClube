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
class BaseDeDadosTest {
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
    private fun insereFilme(db: SQLiteDatabase, filme: Filme){
        filme.id = TabelaBDFilmes(db).insert(filme.toContentValues())
        assertNotEquals(-1, filme.id)
    }
    private fun insereCliente(db: SQLiteDatabase, cliente: Cliente){
        cliente.id = TabelaBDCliente(db).insert(cliente.toContentValues())
        assertNotEquals(-1, cliente.id)
    }


    @Before
    fun apagaBaseDados(){
        appContext().deleteDatabase(BDVideoClubeOpenHelper.NOME)
    }

    @Test
    fun consegueAbrirBaseDados(){
        val openHelper = BDVideoClubeOpenHelper(appContext())
        val db = openHelper.readableDatabase

        assertTrue(db.isOpen)

        db.close()
    }

    @Test
    fun consegueInserirCategoria(){
        val db = getWritableDatabase()

        insereCategoria(db, Categoria("Animação"))

        db.close()
    }

    @Test
    fun consegueInserirFilmes(){
        val db = getWritableDatabase()

        val categoria = Categoria("Animação")
        insereCategoria(db, categoria)

        val filme = Filme("Carros", "2006", "117", categoria)
        insereFilme(db, filme)

        db.close()
    }
    @Test
    fun consegueInserirCliente(){
        val db = getWritableDatabase()

        val cliente = Cliente("Armando Azevedo", "900000000")
        insereCliente(db, cliente)

        db.close()
    }


}