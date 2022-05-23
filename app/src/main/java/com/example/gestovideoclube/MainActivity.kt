package com.example.gestovideoclube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val recyclerView = findViewById<RecyclerView>(R.id.RecyclerView);
        val add = findViewById<Button>(R.id.add_button);
        add.setOnClickListener{
            openAdicionarFilme()
        }
    }

    private fun openAdicionarFilme() {
        val intent = Intent(this, adicionar_filmes::class.java)
        startActivity(intent)
    }

}