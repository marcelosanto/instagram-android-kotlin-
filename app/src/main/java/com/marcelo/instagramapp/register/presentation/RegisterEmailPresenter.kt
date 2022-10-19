package com.marcelo.instagramapp.register.presentation

import android.util.Patterns
import com.marcelo.instagramapp.R
import com.marcelo.instagramapp.register.RegisterEmail
import com.marcelo.instagramapp.register.data.RegisterEmailCallback
import com.marcelo.instagramapp.register.data.RegisterEmailRepository

class RegisterEmailPresenter(
    private var view: RegisterEmail.View?,
    private val repository: RegisterEmailRepository
) : RegisterEmail.Presenter {
    override fun create(email: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (!isEmailValid) {
            view?.displayEmailFailure(R.string.invalid_email)
        } else {
            view?.displayEmailFailure(null)
        }

        if (isEmailValid) {
            view?.showProgress(true)

            repository.create(email, object : RegisterEmailCallback {
                override fun onSuccess() {
                    view?.goToNameAndPasswordScreen(email)
                }

                override fun onFailure(message: String) {
                    view?.onEmailFailure(message)
                }

                override fun onComplete() {
                    view?.showProgress(false)

                }

            })
        }
    }

    override fun onDestroy() {
        view = null
    }
}