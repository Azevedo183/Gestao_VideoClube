package com.example.gestovideoclube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.gestovideoclube.databinding.ActivityAddFilmeBinding

class add_filme : AppCompatActivity() {

    private lateinit var binding: ActivityAddFilmeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_filme)

        binding = ActivityAddFilmeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val itens = listOf("Terror", "Ação", "Misterio", "Drama")
        val adapter = ArrayAdapter(this, R.layout.list_item, itens)
        binding.dropdownField.setAdapter(adapter)
    }
}