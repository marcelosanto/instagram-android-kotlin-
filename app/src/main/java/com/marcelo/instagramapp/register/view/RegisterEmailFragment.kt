package com.marcelo.instagramapp.register.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.marcelo.instagramapp.R
import com.marcelo.instagramapp.databinding.FragmentRegisterEmailBinding
import com.marcelo.instagramapp.register.RegisterEmail

class RegisterEmailFragment : Fragment(R.layout.fragment_register_email), RegisterEmail.View {

    private var binding: FragmentRegisterEmailBinding? = null
    override lateinit var presenter: RegisterEmail.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterEmailBinding.bind(view)
    }

    override fun displayEmailFailure(emailError: Int?) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

}