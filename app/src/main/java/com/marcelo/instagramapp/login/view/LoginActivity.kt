package com.marcelo.instagramapp.login.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.marcelo.instagramapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val editEmail = binding.loginEditEmail
        val editPassword = binding.loginEditPassword

        editEmail.addTextChangedListener(watcher)
        editPassword.addTextChangedListener(watcher)

        binding.loginBtnEntrar.setOnClickListener {
            editEmail.error = "Esse e-mail é inválido"
            editPassword.error = "Essa senha é inválida"
        }
    }

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.loginBtnEntrar.isEnabled = s.toString().isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }
}