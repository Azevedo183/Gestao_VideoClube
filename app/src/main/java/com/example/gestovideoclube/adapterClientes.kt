package com.example.gestovideoclube

import android.database.Cursor
import android.view.View
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

    inner class ViewHolderClientes(itemClientes: View) : RecyclerView.ViewHolder(itemClientes), View.OnClickListener{
        val textViewNome = itemClientes.findViewById<TextView>(R.id.text)
    }

}