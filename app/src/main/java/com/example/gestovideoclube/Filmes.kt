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
import com.example.gestovideoclube.databinding.FragmentFilmesBinding


class Filmes : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    var filmeSelecionado : Filmes? = null
        get() = field
        set(value) {
            field = value

        }


    private var _binding: FragmentFilmesBinding? = null
    private var adapterFilmes : adapterFilmes? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFilmesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_FILME, null, this)

        adapterFilmes = adapterFilmes(  this)
        binding.recyclerViewFilmes.adapter = adapterFilmes
        binding.recyclerViewFilmes.layoutManager = LinearLayoutManager(requireContext())


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderFilmes.ENDERECO_NOME,
            TabelaBDFilmes.TODAS_COLUNAS,
            null,
            null,
            "${TabelaBDFilmes.NOME}"
        )


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterFilmes!!.cursor = data
    }


    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (_binding == null) return
        adapterFilmes!!.cursor = null
    }


    companion object {
        const val ID_LOADER_FILME = 0
    }

}