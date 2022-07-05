package com.example.gestovideoclube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gestovideoclube.databinding.ActivityAddClienteBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class add_cliente : AppCompatActivity() {
    private var _binding: add_cliente? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cliente)
        val cliente_registar = findViewById<Button>(R.id.cliente_registar)
        cliente_registar.setOnClickListener {
            guardar()
        }
    }
    private fun guardar() {
        val titulo = binding.cliente__.text.toString()
        if (titulo.isBlank()) {
            binding.editTextTitulo.error = getString(R.string.titulo_obrigatorio)
            binding.editTextTitulo.requestFocus()
            return
        }

        val autor = binding.editTextAutor.text.toString()
        if (autor.isBlank()) {
            binding.editTextAutor.error = getString(R.string.autor_obrigatorio)
            binding.editTextAutor.requestFocus()
            return
        }

        val idCategoria = binding.spinnerCategorias.selectedItemId
        if (idCategoria == Spinner.INVALID_ROW_ID) {
            binding.textViewCategoria.error = getString(R.string.categoria_obrigatoria)
            binding.spinnerCategorias.requestFocus()
            return
        }

        val livroGuardado =
            if (livro == null) {
                insereCliente(titulo, autor, idCategoria)
            } else {
                alteraLivro(titulo, autor, idCategoria)
            }

        if (livroGuardado) {
            Toast.makeText(requireContext(), R.string.livro_guardado_sucesso, Toast.LENGTH_LONG)
                .show()
            voltaListaLivros()
        } else {
            Snackbar.make(binding.editTextTitulo, R.string.erro_guardar_livro, Snackbar.LENGTH_INDEFINITE).show()
            return
        }
    }
}
