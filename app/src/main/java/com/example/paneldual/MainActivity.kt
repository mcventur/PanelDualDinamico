package com.example.paneldual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.example.paneldual.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var isDualPane: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Si la actividad contiene el id del segundo contenedor de fragment, es la tablet
        isDualPane = findViewById<FragmentContainerView>(R.id.fragmentColores) != null


        if (savedInstanceState == null) {
            val fragmentContainer = binding.fragmentBotones
            val botonesFragment = BotonesFragment()
            supportFragmentManager
                .beginTransaction() //empezar una transacción
                .add(fragmentContainer!!.id, botonesFragment)
                .commit()

            //Si es una tablet (panel dual)
            if(isDualPane){
                val fragmentContainer = binding.fragmentColores
                val colorFragment = ColorFragment()
                supportFragmentManager
                    .beginTransaction() //empezar una transacción
                    .add(fragmentContainer!!.id, botonesFragment)
                    .commit()
            }
        }
    }
}