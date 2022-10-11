package com.marcelo.instagramapp.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.marcelo.instagramapp.common.base.DependencyInjector
import com.marcelo.instagramapp.common.util.TxtWatcher
import com.marcelo.instagramapp.databinding.ActivityLoginBinding
import com.marcelo.instagramapp.login.Login
import com.marcelo.instagramapp.login.presentation.LoginPresenter
import com.marcelo.instagramapp.main.MainActivity
import com.marcelo.instagramapp.register.view.RegisterActivity

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding

    override lateinit var presenter: Login.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = LoginPresenter(this, DependencyInjector.loginRepository())

        with(binding) {
            loginEditEmail.addTextChangedListener(watcher)
            loginEditEmail.addTextChangedListener(TxtWatcher {
                displayEmailFailure(null)
            })

            loginEditPassword.addTextChangedListener(watcher)
            loginEditPassword.addTextChangedListener(TxtWatcher {
                displayPasswordFailure(null)
            })

            loginBtnEntrar.setOnClickListener {
                presenter.login(loginEditEmail.text.toString(), loginEditPassword.text.toString())
            }

            loginTxtRegister.setOnClickListener {
                goToRegisterScreen()
            }
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private val watcher = TxtWatcher {
        binding.loginBtnEntrar.isEnabled = binding.loginEditEmail.text.toString()
            .isNotEmpty() && binding.loginEditPassword.text.toString().isNotEmpty()
    }

    private fun goToRegisterScreen() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    override fun showProgress(enabled: Boolean) {
        binding.loginBtnEntrar.showProgress(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.loginEditEmail.error = emailError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding.loginEditPassword.error = passwordError?.let { getString(it) }
    }

    override fun onUserAuthenticated() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onUserUnauthorized(message: String) {
        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()
    }
}