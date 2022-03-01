package mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.gallery

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentGalleryBinding
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.guardar.setOnClickListener {
            guardarEnArchivo()
        }

        binding.leer.setOnClickListener {
            leerDesdeArchivo()
        }

        return root
    }

    private fun leerDesdeArchivo() {
        try {

            val archivo = InputStreamReader(requireActivity().openFileInput("archivo.txt"))
            var listaContenido = archivo.readLines()

            binding.nombrecompleto.setText(listaContenido.get(0))
            binding.domicilio.setText(listaContenido.get(1))
            binding.telefono.setText(listaContenido.get(2))
            binding.fecha.setText(listaContenido.get(3))

            //AlertDialog.Builder(requireContext())
            //  .setMessage(listaContenido.toString()).show()

        }catch (e: Exception){
            AlertDialog.Builder(requireContext())
                .setMessage(e.message).show()
        }
    }

    private fun guardarEnArchivo() {
        try {

            val archivo = OutputStreamWriter(requireActivity().openFileOutput("archivo.txt",0))
            var cadena = binding.nombrecompleto.text.toString()+"\n"+
                    binding.domicilio.text.toString()+"\n"+
                    binding.telefono.text.toString()+"\n"+
                    binding.fecha.text.toString()

            archivo.write(cadena)
            archivo.flush()
            archivo.close()
            binding.nombrecompleto.setText("")
            binding.domicilio.setText("")
            binding.telefono.setText("")
            binding.fecha.setText("")

            AlertDialog.Builder(requireContext())
                .setMessage("SE GUARDO CORRECTAMENTE").show()

        }catch (e: Exception){
            AlertDialog.Builder(requireContext())
                .setMessage(e.message).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}