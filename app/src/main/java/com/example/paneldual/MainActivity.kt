package com.example.paneldual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentContainerView
import androidx.window.core.layout.WindowSizeClass
import androidx.window.layout.WindowMetricsCalculator
import com.example.paneldual.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), BotonesListener {
    private lateinit var binding: ActivityMainBinding
    private var isDualPane: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Si la actividad contiene el id del segundo contenedor de fragment, es la tablet
        isDualPane = binding.fragmentColores != null
        Log.d("MainActivity", "Es panel dual?: $isDualPane")
        checkScreenWidth()


        if (savedInstanceState == null) {
            //El panel botones se carga en el fragmentBotones (si es una tableta) o en el fragmentMain (si es un móvil)
            val fragmentContainer = if(isDualPane) binding.fragmentBotones else binding.fragmentMain
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
                    .add(fragmentContainer!!.id, colorFragment)
                    .commit()
            }
        }
    }

    fun checkScreenWidth(){
        val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
        val width = metrics.bounds.width()
        val height = metrics.bounds.height()
        val density = resources.displayMetrics.density
        val dpWidth = width/density;
        Log.d("MainActivity","Width calculado: $dpWidth")
        val windowSizeClass = WindowSizeClass.compute(width/density, height/density)

        // COMPACT, MEDIUM, or EXPANDED
        val widthWindowSizeClass = windowSizeClass.windowWidthSizeClass
        // COMPACT, MEDIUM, or EXPANDED
        val heightWindowSizeClass = windowSizeClass.windowHeightSizeClass
        Log.d("MainActivity","WidthWindowSizeClass: $widthWindowSizeClass")

        // Use widthWindowSizeClass and heightWindowSizeClass.

    }

    override fun onClickButton(color: Int) {
        //Recupero la posible isntancia de ColorFragment, si existe (en tableta) y lo casteo a ColorFragment para poder llamar a sus funciones específicas
        //ya que findFragmentById me devuelve un Fragment genérico
        //Como puedo estar lanzando esta función desde un móvil, la referencia podría ser nulla, así que el casting es a un ColorFragment nullable
        val loadedColorFragment = supportFragmentManager.findFragmentById(R.id.fragmentColores) as ColorFragment?
        loadedColorFragment?.cambiarColor(color)
        //Si estamos en un móvil, no tableta, y no está cargado el fragmento. Lo cargamos ahora
        //Podríamos haber usado el atributo isDualPanel. Pero esta es otra forma
        if(loadedColorFragment == null){
            val newColorFragment = ColorFragment.newInstance(color)
            val fragmentContainer = binding.fragmentMain
            supportFragmentManager
                .beginTransaction()
                .replace(fragmentContainer!!.id, newColorFragment)
                .addToBackStack(null)
                .commit()
        }


    }
}