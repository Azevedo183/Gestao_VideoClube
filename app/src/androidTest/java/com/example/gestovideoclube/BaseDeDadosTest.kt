package com.example.gestovideoclube

import android.database.sqlite.SQLiteDatabase
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

    @Test
    fun consegueAlterarCategoria(){
        val db = getWritableDatabase()

        val categoria = Categoria("Animação")
        insereCategoria(db, categoria)

        categoria.nome = "Terror"
        val registosAlterados = TabelaBDCategorias(db).update(
            categoria.toContentValues(),
            "${TabelaBDCategorias.CAMPO_ID}=?",
            arrayOf("${categoria.id}")
        )

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueAlterarFilme(){
        val db = getWritableDatabase()

        val categoriaThriller = Categoria("Thriller")
        insereCategoria(db, categoriaThriller)
        val categoriaMisterio = Categoria("Misterio")
        insereCategoria(db, categoriaMisterio)

        val filme = Filme("Test", "2000","60", categoriaThriller)
        insereFilme(db, filme)

        filme.titulo = "Morte no Nilo"
        filme.ano = "2022"
        filme.duracao = "127"
        filme.categoria = categoriaMisterio

        val registosAlterados = TabelaBDFilmes(db).update(
            filme.toContentValues(),
            "${TabelaBDFilmes.CAMPO_ID}=?",
            arrayOf("${filme.id}")
        )

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueAlterarCliente(){
        val db = getWritableDatabase()

        val cliente = Cliente("José", "8")
        insereCliente(db, cliente)

        cliente.nome = "Alberto"
        cliente.numero_telefone = "9"

        val registosAlterados = TabelaBDCliente(db).update(
            cliente.toContentValues(),
            "${TabelaBDCliente.CAMPO_ID}=?",
            arrayOf("${cliente.id}")
        )

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueEliminarCategoria(){
        val db = getWritableDatabase()

        val categoria = Categoria("Teste")
        insereCategoria(db,categoria)

        val registosEliminados = TabelaBDCategorias(db).delete(
            "${TabelaBDCategorias.CAMPO_ID}=?",
            arrayOf("${categoria.id}")
        )

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueEliminarFilmes(){
        val db = getWritableDatabase()

        val categoria = Categoria("Documentario")
        insereCategoria(db, categoria)

        val filme = Filme("13th","2016","100",categoria)
        insereFilme(db, filme)

        val registosEliminados = TabelaBDFilmes(db).delete(
            "${TabelaBDFilmes.CAMPO_ID}=?",
            arrayOf("${filme.id}")
        )

        assertEquals(1,registosEliminados)

        db.close()
    }

    @Test
    fun consegueEliminarCliente(){
        val db = getWritableDatabase()

        val cliente = Cliente("Rodrigo Alberto","987456123")
        insereCliente(db, cliente)

        val registoEliminados = TabelaBDCliente(db).delete(
            "${TabelaBDCliente.CAMPO_ID}=?",
            arrayOf("${cliente.id}")
        )

        assertEquals(1, registoEliminados)

        db.close()
    }

    @Test
    fun consegueLerCategorias() {
        val db = getWritableDatabase()

        val categoria = Categoria("Aventura")
        insereCategoria(db, categoria)

        val cursor = TabelaBDCategorias(db).query(
            TabelaBDCategorias.TODAS_COLUNAS,
            "${TabelaBDCategorias.CAMPO_ID}=?",
            arrayOf("${categoria.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val categoriaBD = Categoria.fromCursor(cursor)
        assertEquals(categoria, categoriaBD)

        db.close()
    }
    @Test
    fun consegueLerFilmes(){
        val db = getWritableDatabase()

        val categoria = Categoria("Épico")
        insereCategoria(db, categoria)

        val filme = Filme("Titanic", "1997","194",categoria)
        insereFilme(db, filme)

        val cursor = TabelaBDFilmes(db).query(
            TabelaBDFilmes.TODAS_COLUNAS,
            "${TabelaBDFilmes.CAMPO_ID}=?",
            arrayOf("${filme.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val filmeBD = Filme.fromCursor(cursor)
        assertEquals(filme, filmeBD)

        db.close()
    }

   // @Test
   // fun consegueLerClientes(){
     //   val db = getWritableDatabase()

     //   val cliente = Cliente("Tania","123456789")
       // insereCliente(db, cliente)

        //val cursor = TabelaBDCliente(db).query(
          //  TabelaBDCliente.TODAS_COLUNAS,
           // "${TabelaBDCliente.CAMPO_ID}=?",
           // arrayOf("${cliente.id}"),
           // null,
           // null,
           // null
       // )

        //assertEquals(1, cursor.count)
        //assertTrue(cursor.moveToNext())

        //val clienteBD = Cliente.fromCursor(cursor)
        //assertEquals(cliente, clienteBD)

//        db.close()
  //  }

}