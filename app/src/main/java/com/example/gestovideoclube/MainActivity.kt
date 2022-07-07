package com.example.gestovideoclube

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gestovideoclube.databinding.ActivityAddFilmeBinding
import com.example.gestovideoclube.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private var cliked = false
    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotacao_abrir)}
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotacao_fechar)}
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.baixo_cima)}
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.cima_baixo)}




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val navigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navigationView.selectedItemId = R.id.filmes
        val navController = findNavController(R.id.fragmentfragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.filmes, R.id.requesitados, R.id.clientes))
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)

        val btn_pop = findViewById<FloatingActionButton>(R.id.add_pop)
        btn_pop.setOnClickListener{
            onAddButtonClicked()
        }

        val filme_add = findViewById<FloatingActionButton>(R.id.add_filme)
        val cliente_add = findViewById<FloatingActionButton>(R.id.add_cliente)

        cliente_add.setOnClickListener {
            setContentView(R.layout.fragment_add_cliente)
        }
        filme_add.setOnClickListener{
            val intent = Intent(this, add_filme::class.java)
            startActivity(intent)
        }
    }

    private fun onAddButtonClicked() {
       add(cliked)
       cliked = !cliked
    }

   private fun add(cliked: Boolean) {
       val add_filme = findViewById<FloatingActionButton>(R.id.add_filme)
       val add_cliente = findViewById<FloatingActionButton>(R.id.add_cliente)
       val abrir = findViewById<FloatingActionButton>(R.id.add_pop)

       if(!this.cliked){
            add_filme.visibility = View.VISIBLE
            add_filme.isClickable = true
            add_filme.startAnimation(fromBottom)
            add_cliente.visibility = View.VISIBLE
            add_cliente.isClickable = true
            add_cliente.startAnimation(fromBottom)
            abrir.startAnimation(rotateOpen)


        } else {
            add_filme.visibility = View.INVISIBLE
            add_filme.isClickable = false
            add_filme.startAnimation(toBottom)
            add_cliente.visibility = View.INVISIBLE
            add_cliente.isClickable = false
            add_cliente.startAnimation(toBottom)
            abrir.startAnimation(rotateClose)
        }
    }
}