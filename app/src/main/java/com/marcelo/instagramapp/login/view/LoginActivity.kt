package com.marcelo.instagramapp.login.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marcelo.instagramapp.common.util.TxtWatcher
import com.marcelo.instagramapp.databinding.ActivityLoginBinding
import com.marcelo.instagramapp.login.Login

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            loginEditEmail.addTextChangedListener(watcher)
            loginEditPassword.addTextChangedListener(watcher)

            loginBtnEntrar.setOnClickListener {
               
            }
        }
    }

    private val watcher = TxtWatcher {
        binding.loginBtnEntrar.isEnabled = it.isNotEmpty()
    }

    override fun showProgress(enabled: Boolean) {
        binding.loginBtnEntrar.showProgress(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.loginEditEmail.error = emailError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding.loginEditEmail.error = passwordError?.let { getString(it) }
    }

    override fun onUserAuthenticated() {
        //ir para a tela principal
    }

    override fun onUserUnauthorized() {
        //mostrar um alerta
    }
}