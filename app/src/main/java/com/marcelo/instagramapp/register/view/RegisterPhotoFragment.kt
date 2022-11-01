package com.marcelo.instagramapp.register.view

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.marcelo.instagramapp.R
import com.marcelo.instagramapp.common.base.DependencyInjector
import com.marcelo.instagramapp.common.view.CropperImageFragment
import com.marcelo.instagramapp.common.view.CustomDialog
import com.marcelo.instagramapp.databinding.FragmentRegisterPhotoBinding
import com.marcelo.instagramapp.register.RegisterPhoto
import com.marcelo.instagramapp.register.presentation.RegisterPhotoPresenter

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo), RegisterPhoto.View {

    private var binding: FragmentRegisterPhotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null
    override lateinit var presenter: RegisterPhoto.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("cropKey") { requestKey, bundle ->
            val uri = bundle.getParcelable<Uri>(CropperImageFragment.KEY_URI)
            onCropImageResult(uri)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterPhotoBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterPhotoPresenter(this, repository)

        binding?.let {
            with(it) {
                registerBtnJump.setOnClickListener {
                    fragmentAttachListener?.goToMainScreen()
                }

                registerBtnAdd.isEnabled = true
                registerBtnAdd.setOnClickListener {
                    openDialog()
                }
            }
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }

    private fun openDialog() {
        val customDialog = CustomDialog(requireContext())

        customDialog.addButton(R.string.photo, R.string.gallery) {
            when (it.id) {
                R.string.photo -> {
                    fragmentAttachListener?.goToCameraScreen()
                }

                R.string.gallery -> {
                    fragmentAttachListener?.goToGalleryScreen()
                }
            }
        }

        customDialog.show()
    }

    private fun onCropImageResult(uri: Uri?) {
        uri?.let {
            val bitmap = if (Build.VERSION.SDK_INT >= 28) {
                val source = ImageDecoder.createSource(requireContext().contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
            }

            binding?.registerImgProfile?.setImageBitmap(bitmap)

            presenter.updateUser(uri)
        }
    }

    override fun onDestroy() {
        binding = null
        fragmentAttachListener = null
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerBtnAdd?.showProgress(enabled)
    }

    override fun onUpdateSuccess() {
        fragmentAttachListener?.goToMainScreen()
    }

    override fun onUpdateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}