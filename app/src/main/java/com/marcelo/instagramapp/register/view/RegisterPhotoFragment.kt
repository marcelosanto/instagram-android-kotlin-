package com.marcelo.instagramapp.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.marcelo.instagramapp.R
import com.marcelo.instagramapp.common.view.CustomDialog

class RegisterPhotoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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