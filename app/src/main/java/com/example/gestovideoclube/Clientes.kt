package com.example.gestovideoclube

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gestovideoclube.databinding.FragmentClientesBinding


class Clientes : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    var clienteSelecionado : Cliente? = null
        get() = field
        set(value) {
            field = value

        }


    private var _binding: FragmentClientesBinding? = null
    private var adapterClientes : adapterClientes? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentClientesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_CLIENTE, null, this)

        adapterClientes = adapterClientes(this)
        binding.recyclerViewClientes.adapter = adapterClientes
        binding.recyclerViewClientes.layoutManager = LinearLayoutManager(requireContext())


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderClientes.ENDERECO_NOME,
            TabelaBDCliente.TODAS_COLUNAS,
            null,
            null,
            "${TabelaBDCliente.nome_cliente}"
        )


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterClientes!!.cursor = data
    }


    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (_binding == null) return
        adapterClientes!!.cursor = null
    }


    companion object {
        const val ID_LOADER_CLIENTE = 0
    }

}