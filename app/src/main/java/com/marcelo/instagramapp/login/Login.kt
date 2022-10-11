package com.marcelo.instagramapp.login

import androidx.annotation.StringRes
import com.marcelo.instagramapp.common.base.BasePresenter
import com.marcelo.instagramapp.common.base.BaseView

interface Login {

    //camda presenter
    interface Presenter : BasePresenter {
        fun login(email: String, password: String)
    }

    // camada View
    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(@StringRes emailError: Int?)
        fun displayPasswordFailure(@StringRes passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized(message: String)


    }
}