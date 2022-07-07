package com.example.gestovideoclube

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapterFilmes(val fragment: Filmes) :RecyclerView.Adapter<adapterFilmes.ViewHolderFilmes>() {
    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value){
                field = value
                notifyDataSetChanged()
            }
        }
    var viewHolderSelecionado : ViewHolderFilmes? = null
    inner class ViewHolderFilmes(itemFilmes: View) : RecyclerView.ViewHolder(itemFilmes), View.OnClickListener {
        val textViewTitulo = itemFilmes.findViewById<TextView>(R.id.textViewCliente)
        val textViewAno = itemFilmes.findViewById<TextView>(R.id.textViewNumero)
        val textViewCategoria = itemFilmes.findViewById<TextView>(R.id.textView2)

        init {
            itemFilmes.setOnClickListener(this)
        }

        var filmes: Filmes? = null
            get() = field
            set(value: Filmes?) {
                field = value

                //textViewTitulo.text = Filmes?.titulo_filme ?: ""
                //textViewAno.text = Filmes?.ano_filme?: ""
                //textViewCategoria.text = Filmes?.categoria?: ""
            }

        override fun onClick(v: View?) {
            viewHolderSelecionado?.desseleciona()
            seleciona()
        }

        private fun seleciona() {
            itemView.setBackgroundResource(android.R.color.holo_orange_light)
            viewHolderSelecionado = this
            fragment.filmeSelecionado = filmes
        }

        private fun desseleciona() {
            itemView.setBackgroundResource(android.R.color.white)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFilmes {
        val itemFilmes = fragment.layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolderFilmes(itemFilmes)
    }


    override fun onBindViewHolder(holder: ViewHolderFilmes, position: Int) {
        cursor!!.moveToPosition(position)
        //holder.filmes = Filmes.fromCursor(cursor!!)
    }


    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }

}