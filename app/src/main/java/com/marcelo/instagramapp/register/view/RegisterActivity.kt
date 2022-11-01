package com.marcelo.instagramapp.register.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.marcelo.instagramapp.R
import com.marcelo.instagramapp.common.extension.hideKeyboard
import com.marcelo.instagramapp.common.view.CropperImageFragment
import com.marcelo.instagramapp.common.view.CropperImageFragment.Companion.KEY_URI
import com.marcelo.instagramapp.databinding.ActivityRegisterBinding
import com.marcelo.instagramapp.main.MainActivity
import com.marcelo.instagramapp.register.view.RegisterNamePasswordFragment.Companion.KEY_EMAIL
import com.marcelo.instagramapp.register.view.RegisterWelcomeFragment.Companion.KEY_NAME
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity(), FragmentAttachListener {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var currentPhoto: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val fragment = RegisterEmailFragment()
        replaceFragment(fragment)


    }

    override fun goToNameAndPasswordScreen(email: String) {
        val args = Bundle()
        args.putString(KEY_EMAIL, email)

        val fragment = RegisterNamePasswordFragment()
        fragment.arguments = args

        replaceFragment(fragment)
    }

    override fun goToWelcomeScreen(name: String) {
        val args = Bundle()
        args.putString(KEY_NAME, name)

        val fragment = RegisterWelcomeFragment()
        fragment.arguments = args

        replaceFragment(fragment)
    }

    override fun goToPhotoScreen() {
        val fragment = RegisterPhotoFragment()
        replaceFragment(fragment)
    }

    override fun goToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { openImageCropper(it) }
        }

    override fun goToGalleryScreen() {
        getContent.launch("image/*")
    }

    private val getCamera =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { saved ->
            openImageCropper(currentPhoto)
        }

    override fun goToCameraScreen() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            val photoFile: File? = try {
                createImageFile()
            } catch (e: IOException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                null
            }

            photoFile?.also {
                val photoUri =
                    FileProvider.getUriForFile(this, "com.marcelo.instagramapp.fileprovider", it)
                currentPhoto = photoUri

                getCamera.launch(photoUri)
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}_", ".jpg", dir)
    }

    private fun replaceFragment(fragment: Fragment) {
        if (supportFragmentManager.findFragmentById(R.id.register_fragment) == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.register_fragment, fragment)
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.register_fragment, fragment)
                addToBackStack(null)
                commit()
            }
        }
        
        hideKeyboard()
    }

    private fun openImageCropper(uri: Uri) {
        val fragment = CropperImageFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_URI, uri)
            }
        }
        replaceFragment(fragment)
    }


}