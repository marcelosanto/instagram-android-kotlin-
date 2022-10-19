package com.marcelo.instagramapp.register

import androidx.annotation.StringRes
import com.marcelo.instagramapp.common.base.BasePresenter
import com.marcelo.instagramapp.common.base.BaseView

interface RegisterNameAndPassword {
    interface Presenter : BasePresenter {
        fun create(email: String, name: String, password: String, passwordConfirm: String)
    }


    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)

        fun displayNameFailure(@StringRes nameError: Int?)

        fun displayPasswordFailure(@StringRes passwordError: Int?)

        fun onCreateSuccess(name: String)

        fun onCreateFailure(message: String)
    }
}