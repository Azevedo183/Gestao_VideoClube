package com.example.gestovideoclube

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView

class adapterClientes(val fragment: Clientes) :RecyclerView.Adapter<adapterClientes.ViewHolderClientes>() {
    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value){
                field = value
                notifyDataSetChanged()
            }
        }
    var viewHolderSelecionado : ViewHolderClientes? = null
    inner class ViewHolderClientes(itemClientes: View) : RecyclerView.ViewHolder(itemClientes), View.OnClickListener {
        val textViewNome = itemClientes.findViewById<TextView>(R.id.textViewCliente)
        val textViewNumero = itemClientes.findViewById<TextView>(R.id.textViewNumero)

        init {
            itemClientes.setOnClickListener(this)
        }

        var clientes: Cliente? = null
            get() = field
            set(value: Cliente?) {
                field = value

                textViewNome.text = clientes?.nome ?: ""
                textViewNumero.text = clientes?.numero_telefone?: ""
            }

        override fun onClick(v: View?) {
            viewHolderSelecionado?.desseleciona()
            seleciona()
        }

        private fun seleciona() {
            itemView.setBackgroundResource(android.R.color.holo_orange_light)
            viewHolderSelecionado = this
            fragment.clienteSelecionado = clientes
        }

        private fun desseleciona() {
            itemView.setBackgroundResource(android.R.color.white)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClientes {
        val itemClientes = fragment.layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolderClientes(itemClientes)
    }


    override fun onBindViewHolder(holder: ViewHolderClientes, position: Int) {
        cursor!!.moveToPosition(position)
        holder.clientes = Cliente.fromCursor(cursor!!)
    }


    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }

}