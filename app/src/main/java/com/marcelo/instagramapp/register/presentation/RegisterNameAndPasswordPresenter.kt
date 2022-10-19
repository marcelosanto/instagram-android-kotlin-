package com.marcelo.instagramapp.register.presentation

import com.marcelo.instagramapp.R
import com.marcelo.instagramapp.register.RegisterNameAndPassword
import com.marcelo.instagramapp.register.data.RegisterCallback
import com.marcelo.instagramapp.register.data.RegisterRepository

class RegisterNameAndPasswordPresenter(
    private var view: RegisterNameAndPassword.View?,
    private val repository: RegisterRepository
) : RegisterNameAndPassword.Presenter {

    override fun create(email: String, name: String, password: String, passwordConfirm: String) {
        val isNameValid = name.length > 3
        val isPasswordValid = password.length >= 8
        val isPasswordConfirmValid = password == passwordConfirm

        if (!isNameValid) {
            view?.displayNameFailure(R.string.invalid_name)
        } else {
            view?.displayNameFailure(null)
        }

        if (!isPasswordValid) {
            view?.displayPasswordFailure(R.string.invalid_password)
        } else {
            view?.displayPasswordFailure(null)

            if (!isPasswordConfirmValid) {
                view?.displayPasswordFailure(R.string.password_not_equal)
            } else {
                view?.displayPasswordFailure(null)
            }
        }

        if (isNameValid && isPasswordValid && isPasswordConfirmValid) {
            view?.showProgress(true)

            repository.create(email, name, password, object : RegisterCallback {
                override fun onSuccess() {
                    view?.onCreateSuccess(name)
                }

                override fun onFailure(message: String) {
                    view?.onCreateFailure(message)
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