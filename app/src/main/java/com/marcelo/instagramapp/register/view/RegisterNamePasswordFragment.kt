package com.marcelo.instagramapp.register.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.marcelo.instagramapp.R
import com.marcelo.instagramapp.common.base.DependencyInjector
import com.marcelo.instagramapp.common.util.TxtWatcher
import com.marcelo.instagramapp.databinding.FragmentRegisterNamePasswordBinding
import com.marcelo.instagramapp.register.RegisterNameAndPassword
import com.marcelo.instagramapp.register.presentation.RegisterNameAndPasswordPresenter

class RegisterNamePasswordFragment : Fragment(R.layout.fragment_register_name_password),
    RegisterNameAndPassword.View {

    private var binding: FragmentRegisterNamePasswordBinding? = null
    override lateinit var presenter: RegisterNameAndPassword.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterNamePasswordBinding.bind(view)
        presenter =
            RegisterNameAndPasswordPresenter(this, DependencyInjector.registerEmailRepository())
        
        val email = arguments?.getString(KEY_EMAIL)
            ?: throw java.lang.IllegalArgumentException("email not found")

        binding?.let {
            with(it) {
                registerTxtLogin.setOnClickListener {
                    activity?.finish()
                }

                registerNameBtnContinue.setOnClickListener {
                    presenter.create(
                        email,
                        registerEditName.text.toString(),
                        registerEditPassword.text.toString(),
                        registerEditPasswordConfirm.text.toString()
                    )
                }

                registerEditName.addTextChangedListener(watcher)
                registerEditPassword.addTextChangedListener(watcher)
                registerEditPasswordConfirm.addTextChangedListener(watcher)

                registerEditName.addTextChangedListener(TxtWatcher {
                    displayNameFailure(null)
                })
                registerEditPassword.addTextChangedListener(TxtWatcher {
                    displayPasswordFailure(null)
                })

                registerEditPasswordConfirm.addTextChangedListener(TxtWatcher {
                    displayPasswordFailure(null)
                })

            }
        }
    }

    companion object {
        const val KEY_EMAIL = "key_email"
    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerNameBtnContinue?.showProgress(enabled)
    }

    override fun displayNameFailure(nameError: Int?) {
        binding?.registerEditNameInput?.error = nameError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding?.registerEditPasswordInput?.error = passwordError?.let { getString(it) }
    }

    override fun onCreateSuccess(name: String) {
        TODO("Not yet implemented")
    }

    override fun onCreateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private val watcher = TxtWatcher {
        binding?.registerNameBtnContinue?.isEnabled =
            binding?.registerEditName?.text.toString().isNotEmpty() &&
                    binding?.registerEditPassword?.text.toString()
                        .isNotEmpty() && binding?.registerEditPasswordConfirm?.text.toString()
                .isNotEmpty()
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

}