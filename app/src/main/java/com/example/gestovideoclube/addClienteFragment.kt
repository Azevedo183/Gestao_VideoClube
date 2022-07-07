package com.example.gestovideoclube

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.example.gestovideoclube.databinding.FragmentAddClienteBinding
import com.google.android.material.snackbar.Snackbar

class addClienteFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    private var _binding: FragmentAddClienteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var cliente: Cliente? = null

    val registar = binding.clienteRegistar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentAddClienteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registar.setOnClickListener{
            guardar()
        }
    }

    private fun guardar() {
        val nome_cliente = binding.clienteNome.text.toString()
        if (nome_cliente.isBlank()) {
            binding.clienteNome.error = getString(com.example.gestovideoclube.R.string.nome_obrigatorio)
            binding.clienteNome.requestFocus()
            return
        }

        val numero_cliente = binding.clienteNumero.text.toString()
        if (numero_cliente.isBlank()) {
            binding.clienteNumero.error = getString(com.example.gestovideoclube.R.string.numero_obrigatorio)
            binding.clienteNumero.requestFocus()
            return
        }

        val clienteGuardado =
            if (cliente == null) {
                insereCliente(nome_cliente, numero_cliente)
            } else {
                alteraCliente(nome_cliente, numero_cliente)
            }

        if (clienteGuardado) {
            Toast.makeText(requireContext(), com.example.gestovideoclube.R.string.cliente_guardado_sucesso, Toast.LENGTH_LONG)
                .show()
            //voltaListaLivros()
        } else {
            Snackbar.make(binding.clienteNome, com.example.gestovideoclube.R.string.erro_guardar_cliente, Snackbar.LENGTH_INDEFINITE).show()
            return
        }
    }

    private fun alteraCliente(nome: String, numero: String) : Boolean {
        val cliente = Cliente(nome, numero)

        val enderecoCliente = Uri.withAppendedPath(ContentProviderClientes.ENDERECO_NOME, "${this.cliente!!.id}")

        val registosAlterados = requireActivity().contentResolver.update(enderecoCliente, cliente.toContentValues(), null, null)

        return registosAlterados == 1
    }

    private fun insereCliente(nome: String, numero: String): Boolean {
        val cliente = Cliente(nome, numero)

        val enderecoClienteInserido = requireActivity().contentResolver.insert(ContentProviderClientes.ENDERECO_NOME, cliente.toContentValues())

        return enderecoClienteInserido != null
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderClientes.ENDERECO_NOME,
            TabelaBDCategorias.TODAS_COLUNAS,
            null,
            null,
            "${TabelaBDCliente.NOME}"
        )

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        TODO("Not yet implemented")
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        TODO("Not yet implemented")
    }



}

