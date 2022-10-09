package com.marcelo.instagramapp.register.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.marcelo.instagramapp.R
import com.marcelo.instagramapp.common.view.CustomDialog
import com.marcelo.instagramapp.databinding.FragmentRegisterPhotoBinding

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo) {

    private var binding: FragmentRegisterPhotoBinding? = null

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterPhotoBinding.bind(view)

        val customDialog = CustomDialog(requireContext())

        customDialog.addButton(R.string.photo, R.string.gallery) {
            when (it.id) {
                R.string.photo -> {
                    Toast.makeText(requireContext(), "Foto mano", Toast.LENGTH_SHORT).show()
                }

                R.string.gallery -> {
                    Toast.makeText(requireContext(), "Galeria Irmao", Toast.LENGTH_SHORT).show()
                }
            }
        }

        customDialog.show()
    }
}