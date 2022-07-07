package com.example.gestovideoclube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import com.example.gestovideoclube.databinding.ActivityAddClienteBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class add_cliente : AppCompatActivity() {
    private var _binding: add_cliente? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    //override fun onCreateView(
       // inflater: LayoutInflater, container: ViewGroup?,
      //  savedInstanceState: Bundle?
    //): View? {
        //_binding = add_cliente.inflate(inflater, container, false)
      //  return binding.root
    //}

    //private fun guardar() {
        //val titulo = binding.cliente__nome.text.toString()
        //if (titulo.isBlank()) {
           //binding.cliente__nome.error = getString(R.string.titulo_obrigatorio)
            //binding.cliente__nome.requestFocus()
          //  return
        //}

        //val autor = binding.cliente_numero..toString()
        //if (autor.isBlank()) {
           // binding.editTextAutor.error = getString(R.string.autor_obrigatorio)
           // binding.editTextAutor.requestFocus()
          //  return
        //}

        //val idCategoria = binding..selectedItemId
        //if (idCategoria == Spinner.INVALID_ROW_ID) {
           // binding.textViewCategoria.error = getString(R.string.categoria_obrigatoria)
            //binding.spinnerCategorias.requestFocus()
          //  return
        //}

        //val livroGuardado =
            //if (livro == null) {
             //   insereCliente(titulo, autor, idCategoria)
           // } else {
            //    alteraLivro(titulo, autor, idCategoria)
          //  }

        //if (livroGuardado) {
            //Toast.makeText(requireContext(), R.string.livro_guardado_sucesso, Toast.LENGTH_LONG)
                //.show()
        //} else {
          //  Snackbar.make(binding.editTextTitulo, R.string.erro_guardar_livro, Snackbar.LENGTH_INDEFINITE).show()
        //    return
      //  }
    //}
}
