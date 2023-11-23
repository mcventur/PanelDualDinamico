package com.example.paneldual

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import com.example.paneldual.databinding.FragmentColorBinding

// the fragment initialization parameters
private const val COLOR_PARAM = "bgcolor"

/**
 * A simple [Fragment] subclass.
 * Use the [ColorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ColorFragment : Fragment() {

    private var _binding: FragmentColorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentColorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val color = arguments?.getInt(COLOR_PARAM) ?: Color.WHITE
        cambiarColor(color)
    }

    fun cambiarColor(@ColorInt color: Int){
        binding.layoutColor.setBackgroundColor(color)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @Int color El color que queremos tener al cargar el fragmento.
         * @return A new instance of fragment ColorFragment.
         */
        @JvmStatic
        fun newInstance(@ColorInt color: Int) =
            ColorFragment().apply {
                arguments = Bundle().apply {
                    putInt(COLOR_PARAM, color)
                }
            }
    }
}