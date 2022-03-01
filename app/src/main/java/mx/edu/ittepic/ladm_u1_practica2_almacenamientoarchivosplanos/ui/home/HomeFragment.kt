package mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.R
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val bAcerca = binding.acercade
        bAcerca.setOnClickListener {
            val imagen = ImageView(requireContext())
            imagen.setImageResource(R.drawable.diente2)

            AlertDialog.Builder(requireContext())
                .setTitle("Acerca de...")
                .setMessage("Aplicación para consultorio dental.\n(C) Luis Jacobo\nTecnológico de Tepic. LADM")
                .setView(imagen)
                .setPositiveButton("OK", {d, i ->})
                .show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}